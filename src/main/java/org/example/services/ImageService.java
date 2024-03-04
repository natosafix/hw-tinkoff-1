package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.domain.Image;
import org.example.domain.Operation;
import org.example.exceptions.ImageNotFoundException;
import org.example.repositories.ImageRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final MinioService minioService;
    private final ImageRepository imageRepository;
    private final OperationService operationService;

    @Cacheable(value = "ImageService::getImage", key = "#link")
    public Image getImage(String link) {
        var image = imageRepository.findById(link).orElseThrow(() -> new ImageNotFoundException(link));

        operationService.logOperation(
                new Operation(
                        String.format("Read image: %s", image),
                        Operation.OperationType.READ,
                        LocalDateTime.now())
        );
        return image;
    }

    public List<Image> getImages(List<String> links) {
        var images = imageRepository.findAllById(links);
        if (images.size() != links.size())
            throw new ImageNotFoundException(String.join(" ", links));//todo
        return images;
    }

    public byte[] downloadImage(String link) throws Exception {
        return minioService.downloadImage(link);
    }

    @Cacheable(value = "ImageService::uploadImage", key = "#file.originalFilename")
    public Image uploadImage(MultipartFile file) throws Exception {
        var image = minioService.uploadImage(file);
        imageRepository.save(image);

        operationService.logOperation(
                new Operation(
                        String.format("Upload image: %s", image),
                        Operation.OperationType.WRITE,
                        LocalDateTime.now())
        );
        return image;
    }
}

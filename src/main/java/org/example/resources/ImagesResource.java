package org.example.resources;

import lombok.RequiredArgsConstructor;
import org.example.dtos.ImageDto;
import org.example.mappers.ImageMapper;
import org.example.services.ImageService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImagesResource {

    private final ImageService service;
    private final ImageMapper mapper;

    @GetMapping("/{link}")
    public ImageDto getImage(@PathVariable String link) {
        return mapper.imageToImageDto(service.getImage(link));
    }

    @GetMapping(value = "/download/{link}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] downloadImage(@PathVariable String link) throws Exception {
        return service.downloadImage(link);
    }

    @PostMapping("/upload")
    public ImageDto uploadImage(MultipartFile file) throws Exception {
        return mapper.imageToImageDto(service.uploadImage(file));
    }
}

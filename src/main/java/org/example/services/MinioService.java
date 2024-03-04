package org.example.services;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.IOUtils;
import org.example.config.MinioProperties;
import org.example.domain.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MinioService {

    private final MinioProperties properties;
    private final MinioClient client;

    public byte[] downloadImage(String link) throws Exception {
        var objectArgs = GetObjectArgs.builder()
                .bucket(properties.getBucket())
                .object(link)
                .build();
        return IOUtils.toByteArray(client.getObject(objectArgs));
    }

    public Image uploadImage(MultipartFile file) throws Exception {
        var fileLink = UUID.randomUUID().toString();

        client.putObject(
                PutObjectArgs.builder()
                        .bucket(properties.getBucket())
                        .object(fileLink)
                        .stream(file.getInputStream(), file.getSize(), properties.getImageSize())
                        .contentType(file.getContentType())
                        .build()
        );

        return new Image(fileLink, file.getOriginalFilename(), file.getSize());
    }
}

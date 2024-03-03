package org.example.mappers;

import org.example.domain.Image;
import org.example.dtos.ImageDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    public ImageDto imageToImageDto(Image image);

    public List<ImageDto> imagesToImageDtos(List<Image> images);

    public Image imageDtoToImage(ImageDto imageDto);
}

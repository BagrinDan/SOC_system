package org.example.service.models.mappers;

import com.lab2.incident.proto.PublishResponse;
import org.example.service.models.response.PublishResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface PublishMapper {
    PublishMapper INSTANCE = Mappers.getMapper(PublishMapper.class);

    @ValueMapping(source = "UNRECOGNIZED", target = "ERROR")
    PublishResponseDto toDto(PublishResponse grpc);

    PublishResponse toGrpc(PublishResponseDto dto);
}

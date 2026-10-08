package org.example.service.models.mappers;


import com.lab2.incident.proto.SubscribeRequest;
import org.example.service.models.request.SubscribeRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubscribeMapper {
    SubscribeMapper INSTANCE = Mappers.getMapper(SubscribeMapper.class);

    SubscribeRequestDto toDto(SubscribeRequest grpc);
    SubscribeRequest toGrpc(SubscribeRequestDto dto);

}

package org.example.service.models.mappers;


import com.lab2.incident.proto.IncidentRequest;
import com.lab2.incident.proto.IncidentResponse;
import org.example.service.models.request.IncidentRequestDto;
import org.example.service.models.response.IncidentResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface IncidentMapper {
    IncidentMapper INSTANCE = Mappers.getMapper(IncidentMapper.class);

    // from RequestDto to RequestProto
    @Mapping(source = "type", target = "type")
    @Mapping(source = "sourceIp", target = "sourceIp")
    @Mapping(source = "payload", target = "payload")
    @Mapping(source = "targetEndpoint", target = "targetEndpoint")
    @Mapping(source = "messageId", target = "messageId")
    @Mapping(source = "timestamp", target = "timestamp")
    IncidentRequest toGrpc(IncidentRequestDto dto);

    // from RequestProto to RequestDto
    @ValueMapping(source = "UNRECOGNIZED", target = "UNKNOWN")
    IncidentRequestDto toDto(IncidentRequest grpc);

    // from ResponseDto to RequestDto
    IncidentResponseDto toDto(IncidentRequestDto dto);


    @Mapping(source = "type", target = "type")
    @Mapping(source = "sourceIp", target = "sourceIp")
    @Mapping(source = "payload", target = "payload")
    @Mapping(source = "targetEndpoint", target = "targetEndpoint")
    @Mapping(source = "messageId", target = "messageId")
    @Mapping(source = "timestamp", target = "timestamp")
    IncidentResponse toGrpc(IncidentResponseDto grpc);
}

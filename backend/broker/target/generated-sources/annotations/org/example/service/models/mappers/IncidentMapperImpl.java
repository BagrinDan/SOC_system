package org.example.service.models.mappers;

import com.lab2.incident.proto.IncidentRequest;
import com.lab2.incident.proto.IncidentResponse;
import com.lab2.incident.proto.VulnerabilityType;
import javax.annotation.processing.Generated;
import org.example.service.models.enums.VulnerabilityEnum;
import org.example.service.models.request.IncidentRequestDto;
import org.example.service.models.response.IncidentResponseDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-07T11:29:53+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.12.1 (Arch Linux)"
)
public class IncidentMapperImpl implements IncidentMapper {

    @Override
    public IncidentRequest toGrpc(IncidentRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        IncidentRequest.Builder incidentRequest = IncidentRequest.newBuilder();

        incidentRequest.setType( vulnerabilityEnumToVulnerabilityType( dto.type() ) );
        incidentRequest.setSourceIp( dto.sourceIp() );
        incidentRequest.setPayload( dto.payload() );
        incidentRequest.setTargetEndpoint( dto.targetEndpoint() );
        incidentRequest.setMessageId( dto.messageId() );
        incidentRequest.setTimestamp( dto.timestamp() );

        return incidentRequest.build();
    }

    @Override
    public IncidentRequestDto toDto(IncidentRequest grpc) {
        if ( grpc == null ) {
            return null;
        }

        IncidentRequestDto.IncidentRequestDtoBuilder incidentRequestDto = IncidentRequestDto.builder();

        incidentRequestDto.type( vulnerabilityTypeToVulnerabilityEnum( grpc.getType() ) );
        incidentRequestDto.sourceIp( grpc.getSourceIp() );
        incidentRequestDto.payload( grpc.getPayload() );
        incidentRequestDto.targetEndpoint( grpc.getTargetEndpoint() );
        incidentRequestDto.messageId( grpc.getMessageId() );
        incidentRequestDto.timestamp( grpc.getTimestamp() );

        return incidentRequestDto.build();
    }

    @Override
    public IncidentResponseDto toDto(IncidentRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        IncidentResponseDto.IncidentResponseDtoBuilder incidentResponseDto = IncidentResponseDto.builder();

        incidentResponseDto.type( dto.type() );
        incidentResponseDto.sourceIp( dto.sourceIp() );
        incidentResponseDto.payload( dto.payload() );
        incidentResponseDto.targetEndpoint( dto.targetEndpoint() );
        incidentResponseDto.messageId( dto.messageId() );
        incidentResponseDto.timestamp( dto.timestamp() );

        return incidentResponseDto.build();
    }

    @Override
    public IncidentResponse toGrpc(IncidentResponseDto grpc) {
        if ( grpc == null ) {
            return null;
        }

        IncidentResponse.Builder incidentResponse = IncidentResponse.newBuilder();

        incidentResponse.setType( vulnerabilityEnumToVulnerabilityType( grpc.type() ) );
        incidentResponse.setSourceIp( grpc.sourceIp() );
        incidentResponse.setPayload( grpc.payload() );
        incidentResponse.setTargetEndpoint( grpc.targetEndpoint() );
        incidentResponse.setMessageId( grpc.messageId() );
        incidentResponse.setTimestamp( grpc.timestamp() );

        return incidentResponse.build();
    }

    protected VulnerabilityType vulnerabilityEnumToVulnerabilityType(VulnerabilityEnum vulnerabilityEnum) {
        if ( vulnerabilityEnum == null ) {
            return null;
        }

        VulnerabilityType vulnerabilityType;

        switch ( vulnerabilityEnum ) {
            case UNKNOWN: vulnerabilityType = VulnerabilityType.UNKNOWN;
            break;
            case SQLi: vulnerabilityType = VulnerabilityType.SQLi;
            break;
            case XSS: vulnerabilityType = VulnerabilityType.XSS;
            break;
            case UNRECOGNIZED: vulnerabilityType = VulnerabilityType.UNRECOGNIZED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + vulnerabilityEnum );
        }

        return vulnerabilityType;
    }

    protected VulnerabilityEnum vulnerabilityTypeToVulnerabilityEnum(VulnerabilityType vulnerabilityType) {
        if ( vulnerabilityType == null ) {
            return null;
        }

        VulnerabilityEnum vulnerabilityEnum;

        switch ( vulnerabilityType ) {
            case UNRECOGNIZED: vulnerabilityEnum = VulnerabilityEnum.UNKNOWN;
            break;
            case UNKNOWN: vulnerabilityEnum = VulnerabilityEnum.UNKNOWN;
            break;
            case SQLi: vulnerabilityEnum = VulnerabilityEnum.SQLi;
            break;
            case XSS: vulnerabilityEnum = VulnerabilityEnum.XSS;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + vulnerabilityType );
        }

        return vulnerabilityEnum;
    }
}

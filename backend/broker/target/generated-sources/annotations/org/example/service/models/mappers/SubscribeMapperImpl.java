package org.example.service.models.mappers;

import com.lab2.incident.proto.SubscribeRequest;
import com.lab2.incident.proto.VulnerabilityType;
import javax.annotation.processing.Generated;
import org.example.service.models.enums.VulnerabilityEnum;
import org.example.service.models.request.SubscribeRequestDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-07T11:29:53+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.12.1 (Arch Linux)"
)
public class SubscribeMapperImpl implements SubscribeMapper {

    @Override
    public SubscribeRequestDto toDto(SubscribeRequest grpc) {
        if ( grpc == null ) {
            return null;
        }

        VulnerabilityEnum type = null;

        type = vulnerabilityTypeToVulnerabilityEnum( grpc.getType() );

        SubscribeRequestDto subscribeRequestDto = new SubscribeRequestDto( type );

        return subscribeRequestDto;
    }

    @Override
    public SubscribeRequest toGrpc(SubscribeRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        SubscribeRequest.Builder subscribeRequest = SubscribeRequest.newBuilder();

        subscribeRequest.setType( vulnerabilityEnumToVulnerabilityType( dto.type() ) );

        return subscribeRequest.build();
    }

    protected VulnerabilityEnum vulnerabilityTypeToVulnerabilityEnum(VulnerabilityType vulnerabilityType) {
        if ( vulnerabilityType == null ) {
            return null;
        }

        VulnerabilityEnum vulnerabilityEnum;

        switch ( vulnerabilityType ) {
            case UNKNOWN: vulnerabilityEnum = VulnerabilityEnum.UNKNOWN;
            break;
            case SQLi: vulnerabilityEnum = VulnerabilityEnum.SQLi;
            break;
            case XSS: vulnerabilityEnum = VulnerabilityEnum.XSS;
            break;
            case UNRECOGNIZED: vulnerabilityEnum = VulnerabilityEnum.UNRECOGNIZED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + vulnerabilityType );
        }

        return vulnerabilityEnum;
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
}

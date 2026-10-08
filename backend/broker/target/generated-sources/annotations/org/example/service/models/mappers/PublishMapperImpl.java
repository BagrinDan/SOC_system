package org.example.service.models.mappers;

import com.lab2.incident.proto.PublishResponse;
import com.lab2.incident.proto.ResponseStatus;
import javax.annotation.processing.Generated;
import org.example.service.models.enums.ResponseStatusEnum;
import org.example.service.models.response.PublishResponseDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-07T11:29:53+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.12.1 (Arch Linux)"
)
public class PublishMapperImpl implements PublishMapper {

    @Override
    public PublishResponseDto toDto(PublishResponse grpc) {
        if ( grpc == null ) {
            return null;
        }

        boolean success = false;
        String messageId = null;
        ResponseStatusEnum status = null;

        success = grpc.getSuccess();
        messageId = grpc.getMessageId();
        status = responseStatusToResponseStatusEnum( grpc.getStatus() );

        PublishResponseDto publishResponseDto = new PublishResponseDto( success, messageId, status );

        return publishResponseDto;
    }

    @Override
    public PublishResponse toGrpc(PublishResponseDto dto) {
        if ( dto == null ) {
            return null;
        }

        PublishResponse.Builder publishResponse = PublishResponse.newBuilder();

        publishResponse.setSuccess( dto.success() );
        publishResponse.setMessageId( dto.messageId() );
        publishResponse.setStatus( responseStatusEnumToResponseStatus( dto.status() ) );

        return publishResponse.build();
    }

    protected ResponseStatusEnum responseStatusToResponseStatusEnum(ResponseStatus responseStatus) {
        if ( responseStatus == null ) {
            return null;
        }

        ResponseStatusEnum responseStatusEnum;

        switch ( responseStatus ) {
            case UNRECOGNIZED: responseStatusEnum = ResponseStatusEnum.ERROR;
            break;
            case ERROR: responseStatusEnum = ResponseStatusEnum.ERROR;
            break;
            case SEND_TO_BROKER: responseStatusEnum = ResponseStatusEnum.SEND_TO_BROKER;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + responseStatus );
        }

        return responseStatusEnum;
    }

    protected ResponseStatus responseStatusEnumToResponseStatus(ResponseStatusEnum responseStatusEnum) {
        if ( responseStatusEnum == null ) {
            return null;
        }

        ResponseStatus responseStatus;

        switch ( responseStatusEnum ) {
            case ERROR: responseStatus = ResponseStatus.ERROR;
            break;
            case SEND_TO_BROKER: responseStatus = ResponseStatus.SEND_TO_BROKER;
            break;
            case UNRECOGNIZED: responseStatus = ResponseStatus.UNRECOGNIZED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + responseStatusEnum );
        }

        return responseStatus;
    }
}

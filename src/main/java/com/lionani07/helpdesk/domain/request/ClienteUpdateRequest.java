package com.lionani07.helpdesk.domain.request;

import com.lionani07.helpdesk.domain.request.validations.Messages;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder(toBuilder = true)
public class ClienteUpdateRequest {

    @NotBlank(message = Messages.FIELD_NOT_BLANK)
    private String nome;
    @NotBlank(message = Messages.FIELD_NOT_BLANK)
    private String cpf;
    @NotBlank(message = Messages.FIELD_NOT_BLANK)
    private String email;
    @NotBlank(message = Messages.FIELD_NOT_BLANK)
    private String senha;

}

package com.lionani07.helpdesk.templates;

import com.lionani07.helpdesk.domain.request.TecnicoCreateRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TecnicoCreateRequestTemplates {

    public TecnicoCreateRequest.TecnicoCreateRequestBuilder builderDefault() {
        return TecnicoCreateRequest.builder()
                .cpf("50613433017")
                .email("tecnico@gmail.com")
                .nome("tecnico")
                .senha("senha");
    }

}

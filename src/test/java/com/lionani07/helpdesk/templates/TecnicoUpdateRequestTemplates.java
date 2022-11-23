package com.lionani07.helpdesk.templates;

import com.lionani07.helpdesk.domain.request.TecnicoUpdateRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TecnicoUpdateRequestTemplates {

    public TecnicoUpdateRequest.TecnicoUpdateRequestBuilder builderDefault() {
        return TecnicoUpdateRequest.builder()
                .cpf("50613433017")
                .email("tecnico@gmail.com")
                .nome("tecnico upated")
                .senha("senha updated");
    }

}

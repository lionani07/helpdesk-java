package com.lionani07.helpdesk.templates;

import com.lionani07.helpdesk.domain.Tecnico;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TecnicoTemplates {

    public Tecnico.TecnicoBuilder builderDefault() {
        return Tecnico.builder()
                .id(1)
                .cpf("50613433017")
                .email("tecnico@gmail.com")
                .nome("tecnico");
    }

}

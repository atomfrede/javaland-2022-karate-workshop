package com.github.atomfrede.javaland.beleidigungsduell.api.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.NativeWebRequest;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-03-09T09:22:11.953756+01:00[Europe/Berlin]")
@Controller
@RequestMapping("${openapi.beleidigungsDuellAdministrations.base-path:}")
public class BeleidigungenApiController implements BeleidigungenApi {

    private final NativeWebRequest request;

    @Autowired
    public BeleidigungenApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}

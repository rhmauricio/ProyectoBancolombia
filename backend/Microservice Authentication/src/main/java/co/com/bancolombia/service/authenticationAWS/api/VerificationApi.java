package co.com.bancolombia.service.authenticationAWS.api;

import co.com.bancolombia.service.authenticationAWS.model.JsonApiBodyResponseErrors;
import co.com.bancolombia.service.authenticationAWS.model.JsonResponseSuccess;
import co.com.bancolombia.service.authenticationAWS.model.VerificationRequest;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Api(value = "verification", description = "verification code API")
public interface VerificationApi {

    @ApiOperation(
            value = "Verificación de código enviado a email",
            nickname = "verifyCode",
            notes = "Verifica el código que se envió al correo del usuario y confirma al usuario en caso de que el código esté correcto",
            response = JsonResponseSuccess.class, tags = {"authentication-service-api-controller",}
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Inquery step success", response = JsonResponseSuccess.class),
            @ApiResponse(code = 409, message = "Conflict. Verification code is not valid", response = JsonApiBodyResponseErrors.class)})
    @RequestMapping(value = "/verification",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<?> verifyCode(@ApiParam(value = "body", required = true) @Valid @RequestBody VerificationRequest body);

    @ApiOperation(
            value = "Reenvío de código de verificación",
            nickname = "resendCode",
            notes = "Reenvía el código de verificación al email o al teléfono del usuario proporcionado",
            response = JsonResponseSuccess.class, tags = {"authentication-service-api-controller",}
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Inquery step success", response = JsonResponseSuccess.class),
            @ApiResponse(code = 401, message = "Not authorized", response = JsonApiBodyResponseErrors.class)})
    @RequestMapping(value = "/resend-code",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<?> resendVerificationCode(@ApiParam(value = "body", required = true) @Valid @RequestBody VerificationRequest body);
}

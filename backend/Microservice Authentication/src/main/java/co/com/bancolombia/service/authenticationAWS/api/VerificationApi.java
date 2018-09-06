package co.com.bancolombia.service.authenticationAWS.api;

import co.com.bancolombia.service.authenticationAWS.model.AuthenticationLoginResponse;
import co.com.bancolombia.service.authenticationAWS.model.JsonApiBodyResponseErrors;
import co.com.bancolombia.service.authenticationAWS.model.LoginRequest;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Api(value = "verification", description = "verification code API")
public interface VerificationApi {

    @ApiOperation(value = "Verificación de código enviado a email", nickname = "loginPost", notes = "Microservicio que consume Dynamo y Cognito con apache camel", response = AuthenticationLoginResponse.class, tags={ "authentication-service-api-controller", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Inquery step success", response = AuthenticationLoginResponse.class),
            @ApiResponse(code = 401, message = "Invalid credentials. user or password are invalid", response = JsonApiBodyResponseErrors.class) })
    @RequestMapping(value = "/login",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<?> loginPost(@ApiParam(value = "body" ,required=true )  @Valid @RequestBody LoginRequest body);
}

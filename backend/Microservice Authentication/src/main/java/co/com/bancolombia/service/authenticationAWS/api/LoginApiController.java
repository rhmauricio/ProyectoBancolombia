package co.com.bancolombia.service.authenticationAWS.api;

import co.com.bancolombia.service.authenticationAWS.aws_delegate.AWSCognitoDelegate;
import co.com.bancolombia.service.authenticationAWS.aws_delegate.AWSDynamoDBDelegate;
import co.com.bancolombia.service.authenticationAWS.model.*;
import com.amazonaws.services.cognitoidp.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-04T21:59:31.662-05:00")

@Controller
public class LoginApiController implements LoginApi {

    private static final Logger log = LoggerFactory.getLogger(LoginApiController.class);

    private final ObjectMapper objectMapper;

    private AWSCognitoDelegate cognitoDelegate;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public LoginApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.cognitoDelegate = new AWSCognitoDelegate();
    }

    public ResponseEntity<?> loginPost(@ApiParam(value = "body" ,required=true )  @Valid @RequestBody LoginRequest body) {
        String accept = request.getHeader("Content-Type");
        if (accept != null && accept.contains("application/json")) {
            try {
                AuthenticationResultType loginResult;
                Credentials userCredentials = body.getCredentials();

                loginResult = cognitoDelegate.authenticateUser(userCredentials.getUser(), userCredentials.getPassword());

                if (loginResult != null) {
                    User userData = AWSDynamoDBDelegate.readRegister(User.class, userCredentials.getUser());
                    userData.setAccessToken(loginResult.getAccessToken());
                    userData.setRefreshToken(loginResult.getRefreshToken());

                    AWSDynamoDBDelegate.updateRegister(User.class, userData.getUserName(), userData);

                    return new ResponseEntity<>(new LoginResponse().success(true), HttpStatus.OK);
                }
            } catch (AWSCognitoIdentityProviderException e) {
                if (e instanceof UserNotConfirmedException) {
                    log.error("Error al iniciar sesión: el usuario no está verificado", e);

                    JsonApiBodyResponseErrors responseError = new JsonApiBodyResponseErrors();
                    List<ErrorDetail> errorsResponse =  new ArrayList<>();
                    ErrorDetail errorDetail = new ErrorDetail();

                    errorDetail.setCode("0001");
                    errorDetail.setDetail("El usuario que intenta loguearse no ha ingresado el código de verificación");
                    errorDetail.setId(body.getHeader().getId());
                    errorDetail.setSource("/login");
                    errorDetail.setStatus(HttpStatus.CONFLICT.toString());
                    errorDetail.setTitle("Usuario no verificado");

                    errorsResponse.add(errorDetail);
                    responseError.setErrors(errorsResponse);
                    return new ResponseEntity<JsonApiBodyResponseErrors>(responseError, HttpStatus.CONFLICT);
                }

                if (e instanceof UserNotFoundException) {
                    log.error("Usuario no existe: el username dado no existe en la DB", e);

                    JsonApiBodyResponseErrors responseError = new JsonApiBodyResponseErrors();
                    List<ErrorDetail> errorsResponse =  new ArrayList<>();
                    ErrorDetail errorDetail = new ErrorDetail();

                    errorDetail.setCode("0002");
                    errorDetail.setDetail("El usuario o la contraseña son incorrectos");
                    errorDetail.setId(body.getHeader().getId());
                    errorDetail.setSource("/login");
                    errorDetail.setStatus(HttpStatus.UNAUTHORIZED.toString());
                    errorDetail.setTitle("Error de credenciales");

                    errorsResponse.add(errorDetail);
                    responseError.setErrors(errorsResponse);
                    return new ResponseEntity<JsonApiBodyResponseErrors>(responseError, HttpStatus.UNAUTHORIZED);
                }

                if (e instanceof NotAuthorizedException) {
                    log.error("Error de credenciales: password incorrecto");

                    JsonApiBodyResponseErrors responseError = new JsonApiBodyResponseErrors();
                    List<ErrorDetail> errorsResponse =  new ArrayList<>();
                    ErrorDetail errorDetail = new ErrorDetail();

                    errorDetail.setCode("0003");
                    errorDetail.setDetail("El usuario o la contraseña son incorrectos");
                    errorDetail.setId(body.getHeader().getId());
                    errorDetail.setSource("/login");
                    errorDetail.setStatus(HttpStatus.UNAUTHORIZED.toString());
                    errorDetail.setTitle("Error de credenciales");

                    errorsResponse.add(errorDetail);
                    responseError.setErrors(errorsResponse);
                    return new ResponseEntity<>(responseError, HttpStatus.UNAUTHORIZED);
                }
            }
        }

        return new ResponseEntity<AuthenticationLoginResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}

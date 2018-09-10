package co.com.bancolombia.service.authenticationAWS.api;

import co.com.bancolombia.service.authenticationAWS.aws_delegate.AWSCognitoDelegate;
import co.com.bancolombia.service.authenticationAWS.aws_delegate.AWSDynamoDBDelegate;
import co.com.bancolombia.service.authenticationAWS.model.*;
import co.com.bancolombia.service.authenticationAWS.proxy.S3ServiceProxy;
import com.amazonaws.services.cognitoidp.model.InvalidPasswordException;
import com.amazonaws.services.cognitoidp.model.TooManyRequestsException;
import com.amazonaws.services.cognitoidp.model.UsernameExistsException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-04T21:59:31.662-05:00")

@Controller
public class RegisterApiController implements RegisterApi {

    private static final Logger log = LoggerFactory.getLogger(RegisterApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private AWSCognitoDelegate cognitoDelegate;

    @Autowired
    private S3ServiceProxy s3ServiceProxy;

    @org.springframework.beans.factory.annotation.Autowired
    public RegisterApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.cognitoDelegate = new AWSCognitoDelegate();
    }

    public ResponseEntity<?> registerPost(@ApiParam(value = "body", required = true) @Valid @RequestBody RegisterRequest body) {
        String accept = request.getHeader("Content-Type");
        if (accept != null && accept.contains("application/json")) {

            // Verifica que el código coincida con el rol antes de continuar
            if (!verifyRoleCode(body.getUserParameters().getRole(), body.getUserParameters().getVerificationCode().intValue())) {
                log.error("Error de parámetros: el código no coincide con el rol");

                JsonApiBodyResponseErrors responseError = new JsonApiBodyResponseErrors();
                List<ErrorDetail> errorsResponse = new ArrayList<ErrorDetail>();
                ErrorDetail errorDetail = new ErrorDetail();

                errorDetail.setCode("0003");
                errorDetail.setDetail("El código proporcionado no corresponde al rol seleccionado");
                errorDetail.setId(body.getHeader().getId());
                errorDetail.setSource("/register");
                errorDetail.setStatus(HttpStatus.CONFLICT.toString());
                errorDetail.setTitle("Error de código de rol");

                errorsResponse.add(errorDetail);
                responseError.setErrors(errorsResponse);
                return new ResponseEntity<JsonApiBodyResponseErrors>(responseError, HttpStatus.CONFLICT);
            }

            try {
                AuthenticationRegisterResponse response = new AuthenticationRegisterResponse();
                JsonResponseSuccess sucessResponse = new JsonResponseSuccess();
                sucessResponse.setSuccess(true);

                cognitoDelegate.signUp(body.getUserParameters());

                // Si se registra exitosámente, se almacena
                // la data del usuario en DB
                User user = new User();
                UserParameters registerParameters = body.getUserParameters();

                user.setUserName(registerParameters.getEmail());
                user.setFirstName(registerParameters.getFirstName());
                user.setLastName(registerParameters.getLastName());
                user.setPhoneNumber(registerParameters.getPhoneNumber());
                user.setRole(registerParameters.getRole());

                AWSDynamoDBDelegate.createRegister(user);

                if (registerParameters.getImgString() != null) {
                    JsonApiS3Request s3Request = new JsonApiS3Request();
                    s3Request.setHeader(new Header()
                            .id("123456")
                            .type("Save image request"));
                    s3Request.setimagedata(registerParameters.getImgString());
                    s3Request.setKeyname(user.getUserName() + "/profile-img");

                    ResponseEntity<?> s3Response = s3ServiceProxy.putPut(s3Request);
                    log.info("Imaged saved through microservice");
                }

                return new ResponseEntity<AuthenticationRegisterResponse>(response.data(sucessResponse), HttpStatus.OK);
            } catch (UsernameExistsException e) {
                log.error("Error al registrar el usuario: ya existe el email", e);

                JsonApiBodyResponseErrors responseError = new JsonApiBodyResponseErrors();
                List<ErrorDetail> errorsResponse = new ArrayList<ErrorDetail>();
                ErrorDetail errorDetail = new ErrorDetail();

                errorDetail.setCode("0001");
                errorDetail.setDetail("No se pudo crear el nuevo usuario");
                errorDetail.setId(body.getHeader().getId());
                errorDetail.setSource("/register");
                errorDetail.setStatus(HttpStatus.CONFLICT.toString());
                errorDetail.setTitle("Usuario ya existe");

                errorsResponse.add(errorDetail);
                responseError.setErrors(errorsResponse);
                return new ResponseEntity<JsonApiBodyResponseErrors>(responseError, HttpStatus.CONFLICT);
            } catch (TooManyRequestsException e) {
                log.error("Muchas peticiones", e);
            } catch (InvalidPasswordException e) {
                log.error("Error al registrar el usuario: el password no cumple con los requisitos", e);

                JsonApiBodyResponseErrors responseError = new JsonApiBodyResponseErrors();
                List<ErrorDetail> errorsResponse = new ArrayList<ErrorDetail>();
                ErrorDetail errorDetail = new ErrorDetail();

                errorDetail.setCode("0002");
                errorDetail.setDetail("El password no cumple con los requisitos de seguridad");
                errorDetail.setId(body.getHeader().getId());
                errorDetail.setSource("/register");
                errorDetail.setStatus(HttpStatus.CONFLICT.toString());
                errorDetail.setTitle("Password inválido");

                errorsResponse.add(errorDetail);
                responseError.setErrors(errorsResponse);
                return new ResponseEntity<JsonApiBodyResponseErrors>(responseError, HttpStatus.CONFLICT);
            }
        }

        return new ResponseEntity<AuthenticationRegisterResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    private boolean verifyRoleCode(String role, int code) {
        switch (role) {
            case "lambda":
                return code == RegisterApi.LAMBDA_CODE;
            case "dynamo":
                return code == RegisterApi.DYNAMO_CODE;
            case "s3":
                return code == RegisterApi.S3_CODE;
            default:
                return false;
        }
    }

}

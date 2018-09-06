package co.com.bancolombia.service.authenticationAWS.api;

import co.com.bancolombia.service.authenticationAWS.aws_delegate.AWSCognitoDelegate;
import co.com.bancolombia.service.authenticationAWS.model.ErrorDetail;
import co.com.bancolombia.service.authenticationAWS.model.JsonApiBodyResponseErrors;
import co.com.bancolombia.service.authenticationAWS.model.JsonResponseSuccess;
import co.com.bancolombia.service.authenticationAWS.model.VerificationRequest;
import com.amazonaws.services.cognitoidp.model.*;
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

@Controller
public class VerificationApiController implements VerificationApi {

    private static final Logger log = LoggerFactory.getLogger(VerificationApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private AWSCognitoDelegate cognitoDelegate;

    @Autowired
    public VerificationApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
        cognitoDelegate = new AWSCognitoDelegate();
    }

    @Override
    public ResponseEntity<?> verifyCode(@ApiParam(value = "body", required = true) @Valid @RequestBody VerificationRequest body) {
        String accept = request.getHeader("Content-Type");

        if (accept.equals("application/json")) {
            try {
                JsonResponseSuccess responseSuccess = new JsonResponseSuccess()
                        .success(true);

                cognitoDelegate.verifyAccessCode(body.getUserName(), body.getVerificationCode());

                return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
            } catch (AWSCognitoIdentityProviderException e) {
                if (e instanceof CodeMismatchException) {
                    log.error("Error de código: el código de verificación proporcionado es incorrecto");

                    JsonApiBodyResponseErrors responseError = new JsonApiBodyResponseErrors();
                    List<ErrorDetail> errorsResponse =  new ArrayList<>();
                    ErrorDetail errorDetail = new ErrorDetail();

                    errorDetail.setCode("0001");
                    errorDetail.setDetail("El código de verificación proporcionado es incorrecto");
                    errorDetail.setId(body.getHeader().getId());
                    errorDetail.setSource("/verification");
                    errorDetail.setStatus(HttpStatus.CONFLICT.toString());
                    errorDetail.setTitle("Error de código");

                    errorsResponse.add(errorDetail);
                    responseError.setErrors(errorsResponse);
                    return new ResponseEntity<>(responseError, HttpStatus.CONFLICT);
                }

                if (e instanceof ExpiredCodeException) {
                    log.error("Error de código: el código de verificación proporcionado ha expirado");

                    JsonApiBodyResponseErrors responseError = new JsonApiBodyResponseErrors();
                    List<ErrorDetail> errorsResponse =  new ArrayList<>();
                    ErrorDetail errorDetail = new ErrorDetail();

                    errorDetail.setCode("0002");
                    errorDetail.setDetail("El código de verificación proporcionado ha expirado");
                    errorDetail.setId(body.getHeader().getId());
                    errorDetail.setSource("/verification");
                    errorDetail.setStatus(HttpStatus.CONFLICT.toString());
                    errorDetail.setTitle("Error de código");

                    errorsResponse.add(errorDetail);
                    responseError.setErrors(errorsResponse);
                    return new ResponseEntity<>(responseError, HttpStatus.CONFLICT);
                }

                else {
                    log.error("Error: ha ocurrido un error tratando de verificar el código", e);

                    JsonApiBodyResponseErrors responseError = new JsonApiBodyResponseErrors();
                    List<ErrorDetail> errorsResponse =  new ArrayList<>();
                    ErrorDetail errorDetail = new ErrorDetail();

                    errorDetail.setCode("0003");
                    errorDetail.setDetail("Ha ocurrido un error interno en el servidor");
                    errorDetail.setId(body.getHeader().getId());
                    errorDetail.setSource("/verification");
                    errorDetail.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
                    errorDetail.setTitle("Error");

                    errorsResponse.add(errorDetail);
                    responseError.setErrors(errorsResponse);
                    return new ResponseEntity<>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

        }

        return new ResponseEntity<JsonApiBodyResponseErrors>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<?> resendVerificationCode(@ApiParam(value = "body", required = true) @Valid @RequestBody VerificationRequest body) {
        String accept = request.getHeader("Content-Type");

        if (accept.equals("application/json")) {
            try {
                cognitoDelegate.resendVerificationCode(body.getUserName());

                return new ResponseEntity<>(new JsonResponseSuccess().success(true), HttpStatus.OK);
            } catch (AWSCognitoIdentityProviderException e) {
                if (e instanceof UserNotFoundException) {

                }

                if (e instanceof CodeDeliveryFailureException) {

                }

                if (e instanceof NotAuthorizedException) {

                }

                else {
                    log.error("Error: ha ocurrido un error tratando de reenviar el código de verificación", e);

                    JsonApiBodyResponseErrors responseError = new JsonApiBodyResponseErrors();
                    List<ErrorDetail> errorsResponse =  new ArrayList<>();
                    ErrorDetail errorDetail = new ErrorDetail();

                    errorDetail.setCode("0004");
                    errorDetail.setDetail("Ha ocurrido un error interno en el servidor");
                    errorDetail.setId(body.getHeader().getId());
                    errorDetail.setSource("/verification");
                    errorDetail.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
                    errorDetail.setTitle("Error");

                    errorsResponse.add(errorDetail);
                    responseError.setErrors(errorsResponse);
                    return new ResponseEntity<>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }

        return new ResponseEntity<JsonApiBodyResponseErrors>(HttpStatus.NOT_IMPLEMENTED);
    }
}

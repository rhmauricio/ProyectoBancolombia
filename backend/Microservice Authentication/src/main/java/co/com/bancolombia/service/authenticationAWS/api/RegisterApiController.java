package co.com.bancolombia.service.authenticationAWS.api;

import co.com.bancolombia.service.authenticationAWS.aws_delegate.AWSCognitoDelegate;
import co.com.bancolombia.service.authenticationAWS.aws_delegate.AWSDynamoDBDelegate;
import co.com.bancolombia.service.authenticationAWS.model.*;
import com.amazonaws.services.cognitoidp.model.TooManyRequestsException;
import com.amazonaws.services.cognitoidp.model.UsernameExistsException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.xspec.S;
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
public class RegisterApiController implements RegisterApi {

    private static final Logger log = LoggerFactory.getLogger(RegisterApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private AWSCognitoDelegate cognitoDelegate;

    private AWSDynamoDBDelegate AWSDynamoDBDelegate;

    @org.springframework.beans.factory.annotation.Autowired
    public RegisterApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.cognitoDelegate = new AWSCognitoDelegate();
    }

    public ResponseEntity<?> registerPost(@ApiParam(value = "body" ,required=true )  @Valid @RequestBody RegisterRequest body) {
        String accept = request.getHeader("Content-Type");
        if (accept != null && accept.contains("application/json")) {
            try {
                AuthenticationRegisterResponse response = new AuthenticationRegisterResponse();
                RegisterResponse sucessResponse = new RegisterResponse();
                sucessResponse.setSuccess(true);


                //cognitoDelegate.signUp(body.getUserParameters());

                //LoginResponse loginResponse=new LoginResponse();
                //List<String> datos = new ArrayList<>();

                return new ResponseEntity<AuthenticationRegisterResponse>(response.data(sucessResponse), HttpStatus.OK);
            } catch (UsernameExistsException e) {
                log.error("Error al registrar el usuario: ya existe el email", e);

                JsonApiBodyResponseErrors responseError = new JsonApiBodyResponseErrors();
                List<ErrorDetail> errorsResponse =  new ArrayList<ErrorDetail>();
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
            }
        }

        return new ResponseEntity<AuthenticationRegisterResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}

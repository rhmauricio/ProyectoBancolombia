package co.com.bancolombia.service.crudAWS.api;

import co.com.bancolombia.service.crudAWS.aws_delegate.AWSDynamoDBDelegate;
import co.com.bancolombia.service.crudAWS.model.*;
import com.amazonaws.AmazonServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-07T19:06:56.409-05:00")

@Controller
public class GetByIDApiController implements GetByIDApi {

    private static final Logger log = LoggerFactory.getLogger(GetByIDApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    private final AWSDynamoDBDelegate awsDynamoDBDelegate;
    private final UserParameters userParameters;

    @org.springframework.beans.factory.annotation.Autowired
    public GetByIDApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.awsDynamoDBDelegate = new AWSDynamoDBDelegate();
        this.userParameters = new UserParameters();
    }

    public ResponseEntity<?> getUserByID(@RequestParam("id") String id) {
        try {
            User user = awsDynamoDBDelegate.readRegister(User.class, id);
            userParameters
                    .email(user.getUserName())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .phoneNumber(user.getPhoneNumber());
            return new ResponseEntity<>(new SingleUserResponseSuccess().data(userParameters), HttpStatus.OK);
        } catch (AmazonServiceException e) {
            JsonApiBodyResponseErrors responseError = new JsonApiBodyResponseErrors();
            List<ErrorDetail> errorsResponse = new ArrayList<>();
            ErrorDetail errorDetail = new ErrorDetail();

            errorDetail.setCode("0001");
            errorDetail.setDetail("Error en buscar por id");
            errorDetail.setId(id);
            errorDetail.setSource("/login");
            errorDetail.setStatus(HttpStatus.CONFLICT.toString());
            errorDetail.setTitle("Datos por id no encontrados");
            errorsResponse.add(errorDetail);
            responseError.setErrors(errorsResponse);
            return new ResponseEntity<JsonApiBodyResponseErrors>(responseError, HttpStatus.BAD_REQUEST);
        }
    }

}

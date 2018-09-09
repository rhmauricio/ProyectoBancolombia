package co.com.bancolombia.service.crudAWS.api;

import co.com.bancolombia.service.crudAWS.aws_delegate.AWSDynamoDBDelegate;
import co.com.bancolombia.service.crudAWS.model.*;
import com.amazonaws.AmazonServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.management.DynamicMBean;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-07T19:06:56.409-05:00")

@Controller
public class UpdateApiController implements UpdateApi {

    private static final Logger log = LoggerFactory.getLogger(UpdateApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final AWSDynamoDBDelegate awsDynamoDBDelegate;

    @org.springframework.beans.factory.annotation.Autowired
    public UpdateApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.awsDynamoDBDelegate = new AWSDynamoDBDelegate();
    }

    public ResponseEntity<?> updatePut(@ApiParam(value = "body", required = true) @Valid @RequestBody UpdateRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                User user = awsDynamoDBDelegate.getRegister(User.class, body.getUserData().getEmail());
                UserParameters userParameters = body.getUserData();
                user.setFirstName(userParameters.getFirstName());
                user.setLastName(userParameters.getLastName());
                user.setPhoneNumber(userParameters.getPhoneNumber());
                user.setUserName(userParameters.getEmail());
                awsDynamoDBDelegate.updateRegister(User.class, body.getUserData().getEmail(), user);
                return new ResponseEntity<>(new JsonResponseSuccess().success(true),HttpStatus.OK);
            } catch (AmazonServiceException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<JsonResponseSuccess>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<JsonApiBodyResponseErrors>(HttpStatus.NOT_IMPLEMENTED);
    }

}

package co.com.bancolombia.service.crudAWS.api;

import co.com.bancolombia.service.crudAWS.aws_delegate.AWSDynamoDBDelegate;
import co.com.bancolombia.service.crudAWS.model.JsonApiBodyResponseErrors;
import co.com.bancolombia.service.crudAWS.model.User;
import co.com.bancolombia.service.crudAWS.model.UserParameters;
import co.com.bancolombia.service.crudAWS.model.UsersResponseSuccess;
import com.amazonaws.AmazonServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-07T19:06:56.409-05:00")

@Controller
public class GetAllApiController implements GetAllApi {

    private static final Logger log = LoggerFactory.getLogger(GetAllApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final AWSDynamoDBDelegate awsDynamoDBDelegate;

    @org.springframework.beans.factory.annotation.Autowired
    public GetAllApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.awsDynamoDBDelegate = new AWSDynamoDBDelegate();

    }

    public ResponseEntity<?> getAllGet() {

        try {
            List<User> list = awsDynamoDBDelegate.getAllRegisters(User.class);
            List<UserParameters> list1= new ArrayList<>();
            for(User user : list ){
                UserParameters userParameters=new UserParameters();
                userParameters.phoneNumber(user.getPhoneNumber())
                        .lastName(user.getLastName())
                        .firstName(user.getFirstName())
                        .email(user.getUserName());
                list1.add(userParameters);
            }
            return new ResponseEntity<>(new UsersResponseSuccess().data(list1), HttpStatus.OK);
        } catch (AmazonServiceException e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<UsersResponseSuccess>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //return new ResponseEntity<JsonApiBodyResponseErrors>(HttpStatus.NOT_IMPLEMENTED);
    }

}

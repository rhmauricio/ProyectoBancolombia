package co.com.bancolombia.service.crudAWS.api;

import co.com.bancolombia.service.crudAWS.model.JsonApiBodyRequest;
import co.com.bancolombia.service.crudAWS.model.JsonApiBodyResponseErrors;
import co.com.bancolombia.service.crudAWS.model.UsersResponseSuccess;
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
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-07T19:06:56.409-05:00")

@Controller
public class GetAllApiController implements GetAllApi {

    private static final Logger log = LoggerFactory.getLogger(GetAllApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public GetAllApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<UsersResponseSuccess> getAllGet() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<UsersResponseSuccess>(objectMapper.readValue("{  \"data\" : [ {    \"firstName\" : \"firstName\",    \"lastName\" : \"lastName\",    \"image\" : \"{}\",    \"password\" : \"password\",    \"phoneNumber\" : \"phoneNumber\",    \"role\" : \"role\",    \"email\" : \"email\",    \"verificationCode\" : 0.80082819046101150206595775671303272247314453125  }, {    \"firstName\" : \"firstName\",    \"lastName\" : \"lastName\",    \"image\" : \"{}\",    \"password\" : \"password\",    \"phoneNumber\" : \"phoneNumber\",    \"role\" : \"role\",    \"email\" : \"email\",    \"verificationCode\" : 0.80082819046101150206595775671303272247314453125  } ]}", UsersResponseSuccess.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<UsersResponseSuccess>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<UsersResponseSuccess>(HttpStatus.NOT_IMPLEMENTED);
    }

}

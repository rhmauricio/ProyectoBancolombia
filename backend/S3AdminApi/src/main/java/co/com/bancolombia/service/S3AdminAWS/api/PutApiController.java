package co.com.bancolombia.service.S3AdminAWS.api;

import co.com.bancolombia.service.S3AdminAWS.model.JsonApiBodyResponseErrors;
import co.com.bancolombia.service.S3AdminAWS.model.JsonApiS3Request;
import co.com.bancolombia.service.S3AdminAWS.model.JsonResponseSuccess;
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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-07T20:01:37.027-05:00")

@Controller
public class PutApiController implements PutApi {

    private static final Logger log = LoggerFactory.getLogger(PutApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public PutApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<JsonResponseSuccess> putPut(@ApiParam(value = "body" ,required=true )  @Valid @RequestBody JsonApiS3Request body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<JsonResponseSuccess>(objectMapper.readValue("{  \"success\" : true,  \"header\" : {    \"id\" : \"id\",    \"type\" : \"type\"  }}", JsonResponseSuccess.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<JsonResponseSuccess>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<JsonResponseSuccess>(HttpStatus.NOT_IMPLEMENTED);
    }

}

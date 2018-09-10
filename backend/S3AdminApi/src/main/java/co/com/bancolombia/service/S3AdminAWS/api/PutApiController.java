package co.com.bancolombia.service.S3AdminAWS.api;

import co.com.bancolombia.service.S3AdminAWS.aws_delegate.AWSS3Delegate;
import co.com.bancolombia.service.S3AdminAWS.model.ErrorDetail;
import co.com.bancolombia.service.S3AdminAWS.model.JsonApiBodyResponseErrors;
import co.com.bancolombia.service.S3AdminAWS.model.JsonApiS3Request;
import co.com.bancolombia.service.S3AdminAWS.model.JsonResponseSuccess;
import com.amazonaws.AmazonServiceException;
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
import software.amazon.ion.IonException;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-07T20:01:37.027-05:00")

@Controller
public class PutApiController implements PutApi {

    private static final Logger log = LoggerFactory.getLogger(PutApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private AWSS3Delegate awss3Delegate;


    @org.springframework.beans.factory.annotation.Autowired
    public PutApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.awss3Delegate = new AWSS3Delegate();
    }

    public ResponseEntity<?> putPut(@ApiParam(value = "body", required = true) @Valid @RequestBody JsonApiS3Request body){
        String accept = "application/json";
        if (accept != null && accept.contains("application/json")) {
            try {
                awss3Delegate.s3Save(body.getKeyname(),body.getimagedata());
                return new ResponseEntity<>(new JsonResponseSuccess().success(true), HttpStatus.OK);
            } catch (IOException e) {
                log.error("Error guardando la imagen de aws");
                JsonApiBodyResponseErrors responseError = new JsonApiBodyResponseErrors();
                List<ErrorDetail> errorsResponse =  new ArrayList<ErrorDetail>();
                ErrorDetail errorDetail = new ErrorDetail();

                errorDetail.setCode("0001");
                errorDetail.setDetail("error interno IOException");
                errorDetail.setId(body.getHeader().getId());
                errorDetail.setSource("/S3");
                errorDetail.setStatus(HttpStatus.CONFLICT.toString());
                errorDetail.setTitle("error con traer imagen");

                errorsResponse.add(errorDetail);
                responseError.setErrors(errorsResponse);
                return new ResponseEntity<JsonApiBodyResponseErrors>(responseError, HttpStatus.CONFLICT);
            } catch (AmazonServiceException e){
                log.error("Error guardando la imagen de aws");
                JsonApiBodyResponseErrors responseError = new JsonApiBodyResponseErrors();
                List<ErrorDetail> errorsResponse =  new ArrayList<ErrorDetail>();
                ErrorDetail errorDetail = new ErrorDetail();

                errorDetail.setCode("0001");
                errorDetail.setDetail("error interno AmazonServiceException");
                errorDetail.setId(body.getHeader().getId());
                errorDetail.setSource("/S3");
                errorDetail.setStatus(HttpStatus.CONFLICT.toString());
                errorDetail.setTitle("error con traer imagen");

                errorsResponse.add(errorDetail);
                responseError.setErrors(errorsResponse);
                return new ResponseEntity<JsonApiBodyResponseErrors>(responseError, HttpStatus.CONFLICT);
            }
        }

        return new ResponseEntity<JsonResponseSuccess>(HttpStatus.NOT_IMPLEMENTED);
    }

}

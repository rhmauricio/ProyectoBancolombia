/**
 * NOTE: This class is auto generated by the swagger code generator program (2.3.1).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package co.com.bancolombia.service.S3AdminAWS.api;

import co.com.bancolombia.service.S3AdminAWS.model.JsonApiBodyResponseErrors;
import co.com.bancolombia.service.S3AdminAWS.model.JsonApiS3Request;
import co.com.bancolombia.service.S3AdminAWS.model.JsonResponseSuccess;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-07T20:01:37.027-05:00")

@Api(value = "put", description = "the put API")
public interface PutApi {

    @ApiOperation(value = "adiciona un objeto a un bucket", nickname = "putPut", notes = "adiciona los datos a el bucket de S3 en AWS", response = JsonResponseSuccess.class, tags={ "adiciona-objeto-api-controller", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Inquery step success", response = JsonResponseSuccess.class),
        @ApiResponse(code = 401, message = "Unathorized. user has not permissions", response = JsonApiBodyResponseErrors.class) })
    @RequestMapping(value = "/put",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<JsonResponseSuccess> putPut(@ApiParam(value = "body" ,required=true )  @Valid @RequestBody JsonApiS3Request body);

}

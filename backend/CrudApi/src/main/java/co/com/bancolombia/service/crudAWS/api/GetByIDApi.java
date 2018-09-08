/**
 * NOTE: This class is auto generated by the swagger code generator program (2.3.1).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package co.com.bancolombia.service.crudAWS.api;

import co.com.bancolombia.service.crudAWS.model.JsonApiBodyRequest;
import co.com.bancolombia.service.crudAWS.model.JsonApiBodyResponseErrors;
import co.com.bancolombia.service.crudAWS.model.SingleUserResponseSuccess;
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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-07T19:06:56.409-05:00")

@Api(value = "getByID", description = "the getByID API")
public interface GetByIDApi {

    @ApiOperation(value = "trae la informacion de un solo usuario", nickname = "getByIDGet", notes = "Trae la informacion de un solo usuario con base en el id proporcionado", response = SingleUserResponseSuccess.class, tags={ "datos-de-un-usuario", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Inquery step success", response = SingleUserResponseSuccess.class),
        @ApiResponse(code = 401, message = "Unathorized. user has not permissions", response = JsonApiBodyResponseErrors.class) })
    @RequestMapping(value = "/getByID",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<SingleUserResponseSuccess> getByIDGet(@ApiParam(value = "body" ,required=true )  @Valid @RequestBody JsonApiBodyRequest body);

}

/**
 * NOTE: This class is auto generated by the swagger code generator program (2.3.1).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package co.com.bancolombia.service.crudAWS.api;

import co.com.bancolombia.service.crudAWS.model.JsonApiBodyResponseErrors;
import co.com.bancolombia.service.crudAWS.model.UsersResponseSuccess;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-07T19:06:56.409-05:00")

@Api(value = "getAll", description = "the getAll API")
public interface GetAllApi {

    @ApiOperation(value = "Trae la informacion de usuarios registrados", nickname = "getAllGet", notes = "Trae la informacion de usuarios registrados", response = UsersResponseSuccess.class, tags = {"lista-usuarios-api-controller",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Inquery step success", response = UsersResponseSuccess.class),
            @ApiResponse(code = 401, message = "Unathorized. user has not permissions", response = JsonApiBodyResponseErrors.class)})
    @RequestMapping(value = "/getAll",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<?> getAllGet();

}

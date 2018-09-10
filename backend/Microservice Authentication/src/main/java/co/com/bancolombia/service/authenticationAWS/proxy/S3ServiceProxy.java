package co.com.bancolombia.service.authenticationAWS.proxy;

import co.com.bancolombia.service.authenticationAWS.model.JsonApiS3Request;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "aws-s3-service", url = "localhost:8095")
public interface S3ServiceProxy {

    @PutMapping("/S3/put")
    ResponseEntity<?> putPut(@RequestBody JsonApiS3Request body);
}

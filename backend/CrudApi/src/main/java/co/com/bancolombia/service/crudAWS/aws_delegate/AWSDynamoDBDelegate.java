package co.com.bancolombia.service.crudAWS.aws_delegate;

import co.com.bancolombia.service.crudAWS.model.User;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.http.HttpStatus;

import java.util.List;

public class AWSDynamoDBDelegate {

    private static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();

    public static DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(client);

    public static <T extends Object> void createRegister(T data) {
        dynamoDBMapper.save(data);
    }

    public static <T extends Object> T readRegister(Class<T> object, Object id) {
        return dynamoDBMapper.load(object, id);
    }

    public static <T extends Object> boolean updateRegister(Class<T> object, Object id, T newData) {
        T register = dynamoDBMapper.load(object, id);

        if (register != null) {
            dynamoDBMapper.save(newData);


            return true;
        }

        return false;
    }

    public static <T extends Object> List<T> getAllRegisters(Class<T> object) {
        List<T> tList = dynamoDBMapper.scan(object, new DynamoDBScanExpression());
        System.out.print("holi");
        return tList;
    }

    public static <T extends Object> T getRegister(Class<T> object, Object id) {
        return dynamoDBMapper.load(object, id);

    }
}

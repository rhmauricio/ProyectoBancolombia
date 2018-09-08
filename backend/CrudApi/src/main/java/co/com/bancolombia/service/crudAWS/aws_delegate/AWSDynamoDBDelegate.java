package co.com.bancolombia.service.crudAWS.aws_delegate;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class AWSDynamoDBDelegate {

    private static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();

    public static DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(client);

    public static <T extends Object> void createRegister(T data) {
        dynamoDBMapper.save(data);
    }

    public static <T extends Object>T readRegister(Class<T> object, Object id) {
        return dynamoDBMapper.load(object, id);
    }

    public static <T extends Object>boolean updateRegister(Class<T> object, Object id, T newData) {
        T register = dynamoDBMapper.load(object, id);

        if (register != null) {
            dynamoDBMapper.save(newData);

            return true;
        }

        return false;
    }
}

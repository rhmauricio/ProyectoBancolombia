package co.com.bancolombia.service.authenticationAWS.aws_delegate;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class AWSDynamoDBDelegate {

    private static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();

    public static DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(client);

    /*
    static void listMyTables() {
        TableCollection<ListTablesResult> tables = dynamoDB.listTables();
        Iterator<Table> iterator = tables.iterator();
        System.out.println("Listing table names");
        while (iterator.hasNext()) {
            Table table = iterator.next();
            System.out.println(table.getTableName());
        }
    }*/

    /*
    static void getTableInformation() {
        System.out.println("Describing " + tableName);
        TableDescription tableDescription = dynamoDB.getTable(tableName).describe();
        System.out.format(
                "Name: %s:\n" + "Status: %s \n" + "Provisioned Throughput (read capacity units/sec): %d \n"
                        + "Provisioned Throughput (write capacity units/sec): %d \n",
                tableDescription.getTableName(), tableDescription.getTableStatus(),
                tableDescription.getProvisionedThroughput().getReadCapacityUnits(),
                tableDescription.getProvisionedThroughput().getWriteCapacityUnits());
    }*/

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

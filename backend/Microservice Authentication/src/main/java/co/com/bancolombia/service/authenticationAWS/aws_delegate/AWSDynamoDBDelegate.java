package co.com.bancolombia.service.authenticationAWS.aws_delegate;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

public class AWSDynamoDBDelegate {
    private class User;

    public  AWSDynamoDBDelegate(){
        user user = new user();
    }


    @DynamoDBTable(tableName="User")
    private class User {

        private String userName;
        private Integer userId;
        private String passwordHash;
        private String openIdToken;
        private String Phone;


        @DynamoDBHashKey(attributeName="username")
        public String getUserName() { return userName; }
        public void setUserName(String userName) { this.userName = userName; }

        @DynamoDBAttribute(attributeName="userid")
        public Integer getUserId() { return userId; }
        public void setUserId(Integer userId) { this.userId = userId; }

        @DynamoDBAttribute(attributeName="passwordhash")
        public String getPasswordHash() { return passwordHash; }
        public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

        @DynamoDBAttribute(attributeName="openidtoken")
        public String getOpenIdToken() { return openIdToken; }
        public void setOpenIdToken(String openIdToken) { this.openIdToken = openIdToken; }



        public User(String userName, Integer userId, String passwordHash, String openIdToken) {
            this.userName = userName;
            this.userId = userId;
            this.passwordHash = passwordHash;
            this.openIdToken = openIdToken;
        }

        public User(){ }
    }





}


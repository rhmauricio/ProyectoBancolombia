package co.com.bancolombia.service.authenticationAWS.aws_delegate;


import co.com.bancolombia.service.authenticationAWS.model.UserParameters;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AWSCognitoDelegate {

    private String USER_POOL_ID;

    private AWSCognitoIdentityProvider cognitoClient;

    private Properties properties;

    public AWSCognitoDelegate() {
        properties = loadConfigProperties();
        cognitoClient = AWSCognitoIdentityProviderClientBuilder.defaultClient();
    }

    public void signUp(UserParameters userParameters) throws UsernameExistsException, TooManyRequestsException {
        if (properties != null) {
            USER_POOL_ID = properties.getProperty("lamda-pool-id");
        } else {
            USER_POOL_ID = "us-east-1_gC5ImxfcC";
        }

        AdminCreateUserRequest congnitoRequest = new AdminCreateUserRequest()
                .withUserPoolId(USER_POOL_ID)
                .withUsername(userParameters.getEmail())
                .withUserAttributes(
                        new AttributeType()
                                .withName("name")
                                .withValue(userParameters.getFirstName()),
                        new AttributeType()
                                .withName("phone_number")
                                .withValue(userParameters.getPhoneNumber()))
                .withDesiredDeliveryMediums(DeliveryMediumType.EMAIL)
                .withForceAliasCreation(false);

        cognitoClient.adminCreateUser(congnitoRequest);
    }

    private Properties loadConfigProperties() {
        Properties properties = new Properties();

        InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream("config.properties");
        try {
            properties.load(inputStream);

            return properties;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

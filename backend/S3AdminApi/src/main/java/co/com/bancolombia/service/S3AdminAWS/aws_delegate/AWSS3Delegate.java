package co.com.bancolombia.service.S3AdminAWS.aws_delegate;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.Base64;
import java.util.List;

public class AWSS3Delegate {

    private final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
    private final static String BUCKET_NAME = "bancolombia.demo-users-images";

    public AWSS3Delegate() {
    }

    public void s3Save(String key_name, String imageBase64) throws AmazonServiceException,IOException{

            byte[] imagebyte;
            BASE64Decoder decoder = new BASE64Decoder();
            imagebyte=decoder.decodeBuffer(imageBase64);
            InputStream inputStream = new ByteArrayInputStream(imagebyte);

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("image");
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, key_name, inputStream,metadata);
            s3.putObject(putObjectRequest);

    }

    public List<S3ObjectSummary> s3List(String bucket_name) {
        ObjectListing ol = s3.listObjects(bucket_name);
        return ol.getObjectSummaries();
    }

    public String s3Get(String key_name) throws AmazonServiceException, IOException {

        /*
        EN s3 se guarda el el objeto de buket, en S3ObjectInputStream se guarda el objeto escpecifico
        en la variable fos se guardan los bytes de el objeto s3
         */
        S3Object o = s3.getObject(BUCKET_NAME, key_name);
        S3ObjectInputStream s3is = o.getObjectContent();
        ByteArrayOutputStream fos = new ByteArrayOutputStream();

        // Tamaño del bufer a leer
        final byte[] read_buf = new byte[1024];

        // Primera lectura
        int read_len = s3is.read(read_buf);

        // Lee todos los bytes del objeto s3is
        while (read_len > 0) {
            fos.write(read_buf, 0, read_len);
            read_len = s3is.read(read_buf);
        }
        s3is.close();


        return Base64.getEncoder()
                .withoutPadding()
                .encodeToString(fos.toByteArray());
    }


}

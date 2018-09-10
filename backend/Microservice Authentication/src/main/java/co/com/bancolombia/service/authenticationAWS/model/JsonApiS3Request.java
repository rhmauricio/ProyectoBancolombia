package co.com.bancolombia.service.authenticationAWS.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * JsonApiS3Request
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-07T20:01:37.027-05:00")

public class JsonApiS3Request {
    @JsonProperty("header")
    private Header header = null;

    @JsonProperty("keyname")
    private String keyname = null;

    @JsonProperty("imagedata")
    private String imagedata = null;

    public JsonApiS3Request header(Header header) {
        this.header = header;
        return this;
    }

    /**
     * Get header
     *
     * @return header
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    @Valid

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    /**
     * Get keyname
     *
     * @return keyname
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull
    @Valid
    public String getKeyname() {
        return keyname;
    }

    public void setKeyname(String keyname) {
        this.keyname = keyname;
    }


    /**
     * Get imagedata
     *
     * @return imagedata
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull
    @Valid
    public String getimagedata() {
        return imagedata;
    }

    public void setimagedata(String imagedata) {
        this.imagedata = imagedata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JsonApiS3Request jsonApiS3Request = (JsonApiS3Request) o;
        return Objects.equals(this.header, jsonApiS3Request.header) &&
               Objects.equals(this.keyname, jsonApiS3Request.keyname) &&
               Objects.equals(this.imagedata, jsonApiS3Request.imagedata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, keyname, imagedata);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class JsonApiS3Request {\n");

        sb.append("    header: ").append(toIndentedString(header)).append("\n");
        sb.append("    keyname: ").append(toIndentedString(keyname)).append("\n");
        sb.append("    imagedata: ").append(toIndentedString(imagedata)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}


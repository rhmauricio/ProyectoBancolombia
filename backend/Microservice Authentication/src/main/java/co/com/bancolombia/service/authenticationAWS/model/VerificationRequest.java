package co.com.bancolombia.service.authenticationAWS.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class VerificationRequest {

    @JsonProperty("header")
    private Header header = null;

    @JsonProperty("verificationCode")
    private String verificationCode = null;

    public VerificationRequest header(Header header) {
        this.header = header;
        return this;
    }

    /**
     * Get header
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

    public VerificationRequest verificationRequest(String verificationCode) {
        this.verificationCode = verificationCode;
        return this;
    }

    /**
     * Get credentials
     * @return credentials
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    @Valid

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VerificationRequest verificationRequest = (VerificationRequest) o;
        return Objects.equals(this.header, verificationRequest.header) &&
                Objects.equals(this.verificationCode, verificationRequest.verificationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, verificationCode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LoginRequest {\n");

        sb.append("    header: ").append(toIndentedString(header)).append("\n");
        sb.append("    credentials: ").append(toIndentedString(verificationCode)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

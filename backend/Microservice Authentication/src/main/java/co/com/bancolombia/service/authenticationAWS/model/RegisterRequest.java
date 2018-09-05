package co.com.bancolombia.service.authenticationAWS.model;

import java.util.Objects;
import co.com.bancolombia.service.authenticationAWS.model.Header;
import co.com.bancolombia.service.authenticationAWS.model.UserParameters;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RegisterRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-04T21:59:31.662-05:00")

public class RegisterRequest   {
  @JsonProperty("header")
  private Header header = null;

  @JsonProperty("userParameters")
  private UserParameters userParameters = null;

  public RegisterRequest header(Header header) {
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

  public RegisterRequest userParameters(UserParameters userParameters) {
    this.userParameters = userParameters;
    return this;
  }

  /**
   * Get userParameters
   * @return userParameters
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public UserParameters getUserParameters() {
    return userParameters;
  }

  public void setUserParameters(UserParameters userParameters) {
    this.userParameters = userParameters;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegisterRequest registerRequest = (RegisterRequest) o;
    return Objects.equals(this.header, registerRequest.header) &&
        Objects.equals(this.userParameters, registerRequest.userParameters);
  }

  @Override
  public int hashCode() {
    return Objects.hash(header, userParameters);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegisterRequest {\n");
    
    sb.append("    header: ").append(toIndentedString(header)).append("\n");
    sb.append("    userParameters: ").append(toIndentedString(userParameters)).append("\n");
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


package co.com.bancolombia.service.crudAWS.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * JsonApiBodyRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-07T19:06:56.409-05:00")

public class JsonApiBodyRequest {
    @JsonProperty("header")
    private Header header = null;

    public JsonApiBodyRequest header(Header header) {
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


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JsonApiBodyRequest jsonApiBodyRequest = (JsonApiBodyRequest) o;
        return Objects.equals(this.header, jsonApiBodyRequest.header);
    }

    @Override
    public int hashCode() {
        return Objects.hash(header);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class JsonApiBodyRequest {\n");

        sb.append("    header: ").append(toIndentedString(header)).append("\n");
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


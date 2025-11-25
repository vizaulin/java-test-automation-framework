package project.core.api.pojo.models;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class ErrorResponse {
    @JsonIgnore
    private final Map<String, Object> responseFields = new HashMap<>();
    @JsonProperty("error")
    private String error;

    public String getError() {
        return error;
    }

    public Map<String, Object> getResponseFields() {
        return responseFields;
    }

    @JsonAnySetter
    public void addField(String key, Object value) {
        responseFields.put(key, value);
    }
}

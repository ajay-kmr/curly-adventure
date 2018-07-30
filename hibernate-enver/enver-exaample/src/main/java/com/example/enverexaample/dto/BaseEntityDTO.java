package com.example.enverexaample.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
abstract public class BaseEntityDTO implements Serializable {

    protected Long id;
    protected Boolean deleted;

    protected Date createdDate;

    protected String createdBy;

    protected Date lastModifiedDate;

    protected String lastModifiedBy;

    @JsonIgnore
    protected Long version;

    @JsonProperty("version")
    public Long getVersion() {
        return version;
    }

    @JsonIgnore
    public void setVersion(Long version) {
        this.version = version;
    }

    @JsonIgnore
    public Boolean getDeleted() {
        return deleted;
    }
}

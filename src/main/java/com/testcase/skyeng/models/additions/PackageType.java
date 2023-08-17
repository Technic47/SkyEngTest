package com.testcase.skyeng.models.additions;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum PackageType {
    @JsonProperty(value = "mail")
    MAIL,
    @JsonProperty(value = "package")
    PACKAGE,
    @JsonProperty(value = "parcel")
    PARCEL,
    @JsonProperty(value = "postcard")
    POSTCARD,
    @JsonEnumDefaultValue
    UNKNOWN
}

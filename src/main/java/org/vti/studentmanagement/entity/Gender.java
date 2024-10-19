package org.vti.studentmanagement.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    Nam(1),
    Nu(2);

    private final int code;

    Gender(int code) {
        this.code = code;
    }

    // Trả về mã số khi serializing (chuyển enum thành JSON)
    @JsonValue
    public int getCode() {
        return code;
    }

    // Tạo enum từ mã số khi deserializing (chuyển JSON thành enum)
    @JsonCreator
    public static Gender fromCode(int code) {
        for (Gender gender : Gender.values()) {
            if (gender.getCode() == code) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Invalid Gender code: " + code);
    }
}

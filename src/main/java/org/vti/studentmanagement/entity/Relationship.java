package org.vti.studentmanagement.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Relationship {
    Bo(1),
    Me(2),
    Anh(3),
    Chi(4),
    Em(5);

    private final int code;


    Relationship(int code) {
        this.code = code;

    }

    // Trả về mã số khi serializing (chuyển enum thành JSON)
    @JsonValue
    public int getCode() {
        return code;
    }

    // Tạo enum từ mã số khi deserializing (chuyển JSON thành enum)
    @JsonCreator
    public static Relationship fromCode(int code) {
        for (Relationship relationship : Relationship.values()) {
            if (relationship.getCode() == code) {
                return relationship;
            }
        }
        throw new IllegalArgumentException("Invalid Relationship code: " + code);
    }


}

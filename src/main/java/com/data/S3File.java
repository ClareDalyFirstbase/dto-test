package com.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class S3File {
    private UUID id;
    private String name;
    private String extension;

    public S3File(final String name) {
        this.name = name;
        this.extension = ".jpg";
    }
}

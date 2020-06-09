package agh.wtm.books.model;

import lombok.*;
import org.bson.types.ObjectId;

@Data
public class Tag {
    private ObjectId id;
    private String name;
    private String color;
}

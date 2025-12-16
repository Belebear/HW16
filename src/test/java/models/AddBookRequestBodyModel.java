package models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class AddBookRequestBodyModel {

    private String userId;
    List<CollectionOfIsbns> collectionOfIsbns;

    @Data
    @Builder
    public static class CollectionOfIsbns {
        private String isbn;
    }
}

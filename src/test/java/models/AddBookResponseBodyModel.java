package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddBookResponseBodyModel {
    private List<Books> books;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Books {
        private String isbn;
    }
}

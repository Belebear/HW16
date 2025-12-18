package specs;

import api.AuthorizationApiSteps;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static helpers.CustomAllureListener.withCustom;

public class RequestSpec {
    public static RequestSpecification baseSpec() {

        return new RequestSpecBuilder()
                .addFilter(withCustom())
                .setBaseUri("https://demoqa.com")
                .addHeader("Authorization", "Bearer " + AuthorizationApiSteps.getToken())
                .log(LogDetail.ALL)
                .setContentType(ContentType.JSON)
                .build();
    }
}

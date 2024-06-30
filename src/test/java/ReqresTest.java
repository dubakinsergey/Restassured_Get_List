import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ReqresTest {
    private final static String URL = "https://reqres.in/";

    @Test
    public void checkSortedList() {
        Specification.installSpecification(Specification.requestSpecification(URL), Specification.responseSpecificationStatus200());
        List<ResourceData> listResource = given()
                .when()
                .get("api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", ResourceData.class);
        List<Integer> years = listResource.stream().map(ResourceData::getYear).collect(Collectors.toList());
//        System.out.println(years);
        List<Integer> sortedListYears = years.stream().sorted().collect(Collectors.toList());
//        System.out.println(sortedListYears);
        Assert.assertEquals(years, sortedListYears);
    }


}

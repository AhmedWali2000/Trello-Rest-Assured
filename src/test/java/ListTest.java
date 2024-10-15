import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static org.hamcrest.Matchers.*;

public class ListTest {
    private SoftAssert softAssert;

    @BeforeMethod
    public void setup() {
        Board.setBoardName("Wali's Board 1");
        List.setListName("Wali's List 1");
        List.setListNewName("Wali's List 2");
        softAssert = new SoftAssert();
    }

    @Test(priority = 1)
    public void createBoard() {
        Board.create().then().log().all().statusCode(200)
                .body("name", equalTo(Board.getBoardName()))
                .body("id", notNullValue());
    }
    @Test(priority = 2)
    public void createList() {
        List.create().then().log().all().statusCode(200)
                .body("name", equalTo(List.getListName()))
                .body("id", notNullValue())
                .body("idBoard", equalTo(Board.getBoardID()));
    }
    @Test(priority = 3)
    public void getList() {
        var response = List.get().then().log().all().statusCode(200).extract().response();
        softAssert.assertEquals(response.path("name"),List.getListName());
        softAssert.assertEquals(response.path("id"),List.getListID());
        softAssert.assertEquals(response.path("idBoard"),Board.getBoardID());
    }
    @Test(priority = 4)
    public void updateList() {
        var response = List.update().then().log().all().statusCode(200).extract().response();
        softAssert.assertEquals(response.path("name"),List.getListNewName());
    }
    @Test(priority = 5)
    public void getNewList() {
        var response = List.get().then().log().all().statusCode(200).extract().response();
        softAssert.assertEquals(response.path("name"),List.getListNewName());
        softAssert.assertEquals(response.path("id"),List.getListID());
        softAssert.assertEquals(response.path("idBoard"),Board.getBoardID());
    }
    @Test(priority = 6)
    public void deleteBoard() {
        Board.delete().then().log().all().statusCode(200);
    }
    @Test(priority = 7)
    public void getDeletedList() {
        List.get().then().log().all().statusCode(404);
    }
}
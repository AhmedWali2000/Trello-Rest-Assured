import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static org.hamcrest.Matchers.*;

public class BoardTest {
    private SoftAssert softAssert;

    @BeforeMethod
    public void setup() {
        Board.setBoardName("Wali's Board 1");
        Board.setNewBoardName("Wali's Board 2");
        softAssert = new SoftAssert();
    }

    @Test(priority = 1)
    public void createBoard() {
        Board.create().then().log().all().statusCode(200)
                .body("name", equalTo(Board.getBoardName()))
                .body("id", notNullValue());
    }
    @Test(priority = 2)
    public void getBoard() {
        var response = Board.get().then().log().all().statusCode(200).extract().response();
        softAssert.assertEquals(response.path("name"),Board.getBoardName());
        softAssert.assertEquals(response.path("id"),Board.getBoardID());
    }
    @Test(priority = 3)
    public void updateBoard() {
        var response = Board.update().then().log().all().statusCode(200).extract().response();
        softAssert.assertEquals(response.path("name"),Board.getNewBoardName());
    }
    @Test(priority = 4)
    public void getNewBoard() {
        var response = Board.get().then().log().all().statusCode(200).extract().response();
        softAssert.assertEquals(response.path("name"),Board.getNewBoardName());
        softAssert.assertEquals(response.path("id"),Board.getBoardID());
    }
    @Test(priority = 5)
    public void deleteBoard() {
        Board.delete().then().log().all().statusCode(200);
    }
    @Test(priority = 6)
    public void getDeletedBoard() {
        Board.get().then().log().all().statusCode(404);
    }
}
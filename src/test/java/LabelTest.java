import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static org.hamcrest.Matchers.*;

public class LabelTest {
    private SoftAssert softAssert;

    @BeforeMethod
    public void setup() {
        Board.setBoardName("Wali's Board 1");
        Label.setLabelName("Wali's Label 1");
        Label.setLabelNewName("Wali's Label 2");
        Label.setLabelNewColor("red");
        softAssert = new SoftAssert();
    }

    @Test(priority = 1)
    public void createBoard() {
        Board.create().then().log().all().statusCode(200)
                .body("name", equalTo(Board.getBoardName()))
                .body("id", notNullValue());
    }
    @Test(priority = 2)
    public void createLabel() {
        Label.create().then().log().all().statusCode(200)
                .body("name", equalTo(Label.getLabelName()))
                .body("color", equalTo(Label.getLabelColor()))
                .body("id", notNullValue())
                .body("idBoard", equalTo(Board.getBoardID()));
    }
    @Test(priority = 3)
    public void getLabel() {
        var response = Label.get().then().log().all().statusCode(200).extract().response();
        softAssert.assertEquals(response.path("name"),Label.getLabelName());
        softAssert.assertEquals(response.path("id"),Label.getLabelID());
        softAssert.assertEquals(response.path("color"),Label.getLabelColor());
        softAssert.assertEquals(response.path("idBoard"),Board.getBoardID());
    }
    @Test(priority = 4)
    public void updateLabel() {
        var response = Label.update().then().log().all().statusCode(200).extract().response();
        softAssert.assertEquals(response.path("name"),Label.getLabelNewName());
        softAssert.assertEquals(response.path("color"),Label.getLabelNewColor());
    }
    @Test(priority = 5)
    public void getNewLabel() {
        var response = Label.get().then().log().all().statusCode(200).extract().response();
        softAssert.assertEquals(response.path("name"),Label.getLabelNewName());
        softAssert.assertEquals(response.path("id"),Label.getLabelID());
        softAssert.assertEquals(response.path("color"),Label.getLabelNewColor());
        softAssert.assertEquals(response.path("idBoard"),Board.getBoardID());
    }
    @Test(priority = 6)
    public void deleteLabel() {
        Label.delete().then().log().all().statusCode(200);
    }
    @Test(priority = 7)
    public void getDeletedLabel() {
        Label.get().then().log().all().statusCode(404);
    }
    @Test(priority = 8)
    public void deleteBoard() {
        Board.delete().then().log().all().statusCode(200);
    }
    @Test(priority = 9)
    public void getDeletedBoard() {
        Board.get().then().log().all().statusCode(404);
    }
}
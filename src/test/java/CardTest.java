import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static org.hamcrest.Matchers.*;

public class CardTest {
    private SoftAssert softAssert;

    @BeforeMethod
    public void setup() {
        Board.setBoardName("Wali's Board 1");
        List.setListName("Wali's List 1");
        Card.setCardName("Wali's Card");
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
    public void createCard() {
        Card.create().then().log().all().statusCode(200)
                .body("id", notNullValue())
                .body("idBoard", equalTo(Board.getBoardID()))
                .body("idList", equalTo(List.getListID()));
    }
    @Test(priority = 4)
    public void getCard() {
        var response = Card.get().then().log().all().statusCode(200).extract().response();
        softAssert.assertEquals(response.path("idBoard"),Board.getBoardID());
        softAssert.assertEquals(response.path("idList"),List.getListID());
        softAssert.assertEquals(response.path("id"),Card.getCardID());
    }
    @Test(priority = 5)
    public void updateCard() {
        var response = Card.update().then().log().all().statusCode(200).extract().response();
        softAssert.assertEquals(response.path("name"),Card.getCardName());
    }
    @Test(priority = 6)
    public void getNewCard() {
        var response = Card.get().then().log().all().statusCode(200).extract().response();
        softAssert.assertEquals(response.path("idBoard"),Board.getBoardID());
        softAssert.assertEquals(response.path("idList"),List.getListID());
        softAssert.assertEquals(response.path("id"),Card.getCardID());
        softAssert.assertEquals(response.path("name"),Card.getCardName());
    }
    @Test(priority = 7)
    public void deleteCard() {
        Card.delete().then().log().all().statusCode(200);
    }
    @Test(priority = 8)
    public void getDeletedCard() {
        Card.get().then().log().all().statusCode(404);
    }
    @Test(priority = 9)
    public void deleteBoard() {
        Board.delete().then().log().all().statusCode(200);
    }
    @Test(priority = 10)
    public void getDeletedBoard() {
        Board.get().then().log().all().statusCode(404);
    }
}
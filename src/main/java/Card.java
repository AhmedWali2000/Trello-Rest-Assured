import io.restassured.response.Response;

public class Card{

    private static String cardID;
    private static String cardName;

    public static String getCardID() {
        return cardID;
    }
    public static String getCardName() {
        return cardName;
    }
    public static void setCardName(String cardName) {
        Card.cardName = cardName;
    }

    public static Response create() {
        Response response = Base.request()
                .queryParam("idList", List.getListID())
                .when().post("cards");
        cardID = response.path("id");
        return response;
    }

    public static Response get() {
        return Base.request()
                .when().get("cards/" + cardID);
    }

    public static Response update() {
        return Base.request()
                .queryParam("name", cardName)
                .when().put("cards/" + cardID);
    }

    public static Response delete() {
        return Base.request()
                .when().delete("cards/" + cardID);
    }

}

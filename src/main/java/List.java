import io.restassured.response.Response;

public class List {
    private static String listID;
    private static String listName;
    private static String listNewName;

    public static void setListID(String listID) {
        List.listID = listID;
    }
    public static String getListID() {
        return listID;
    }
    public static String getListName() {
        return listName;
    }
    public static String getListNewName() {
        return listNewName;
    }
    public static void setListName(String listName) {
        List.listName = listName;
    }
    public static void setListNewName(String listNewName) {
        List.listNewName = listNewName;
    }

    public static Response create() {
         Response response = Base.request()
                .queryParam("name", listName)
                .queryParam("idBoard", Board.getBoardID())
                .when().post("lists");
        listID = response.path("id");
        return response;
    }

    public static Response get() {
        return Base.request()
                .when().get("lists/" + listID);
    }

    public static Response update() {
        return Base.request()
                .queryParam("name", listNewName)
                .when().put("lists/" + listID);
    }

}

package DomoRestAssuredAPI;

import files.PayLoad;
import io.restassured.path.json.JsonPath;

public class ReadJsonFile {

    public static void main(String[] args) {
        JsonPath js = new JsonPath(PayLoad.coursePrice());

        int amount = js.getInt("dashboard.purchaseAmount");
        System.out.println(amount);

        String webSiteName = js.getString("dashboard.website");
        System.out.println(webSiteName);

        for (int i=0; i<js.getInt("courses.size()");i++) {
            String courseName = js.get("courses["+i+"].title");
            int price = js.getInt("courses["+i+"].price");
            int copies = js.getInt("courses["+i+"].copies");
            int total = price*copies;

            System.out.println("\n"+courseName);
            System.out.println(price);
            System.out.println(copies+"\n");
            System.out.println("Total Amount : "+total+"\n");
        }

        System.out.println("----------- select coures -------------------");

        for (int i=0; i<js.getInt("courses.size()");i++) {
            String courseName = js.get("courses["+i+"].title");
            if (courseName.equals("Cypress")) {
                int price = js.getInt("courses[" + i + "].price");
                int copies = js.getInt("courses[" + i + "].copies");
                int total = price * copies;

                System.out.println("\n" + courseName);
                System.out.println(price);
                System.out.println(copies + "\n");
                System.out.println("Total Amount : " + total + "\n");
            }
        }


    }

}

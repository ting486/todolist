package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class ReadWebPage {
//    public static List<String> printed = new ArrayList<>();

    public ReadWebPage() {

    }

    public void readUrl(String stockCode) throws IOException {
        // cred: https://stackoverflow.com/questions/4308554/simplest-way-to-read-json-from-a-url-in-java

//        String stockCode = "AAPL";
        String myUrl = "https://financialmodelingprep.com/api/v3/company/profile/" + stockCode;
        URL url = new URL(myUrl);
        URLConnection request = url.openConnection();
        request.connect();

        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject rootObj = root.getAsJsonObject();
        JsonElement profile = rootObj.get("profile");
        String price = profile.getAsJsonObject().get("price").getAsString();
        String changesPercentage = profile.getAsJsonObject().get("changesPercentage").getAsString();

        String toPrint = "Simple stock information for " + stockCode + " right now: \n"
                + "price: $" + price + "\n" + "change in percentage: " + changesPercentage;

//        System.out.println("Simple stock information for " + stockCode + " right now: ");
//        System.out.println("price: $" + price);
//        System.out.println("change in percentage: " + changesPercentage);
        System.out.println(toPrint);

//        printed.add(toPrint);
    }

//    public static List<String> getPrinted() {
//        return printed;
//    }

}

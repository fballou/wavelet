import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

public class SearchEngine implements URLHandler{
    ArrayList<String> stringz = new ArrayList<String>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return stringz.toString();
        } 
        if (url.getPath().contains("/add")) {
            System.out.println("Path: " + url.getPath());
            String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    stringz.add(parameters[1]);
                    return parameters[1] + "query has been added";
                }
            
            // stringz.add(url.getQuery());
            // System.out.println(url.getPath());
            // return String.format("Query added!");
            return "404 Not Found!";
        } 
        else {
            if (url.getPath().equals("/search")) {
                ArrayList<String> temp = new ArrayList<>();
                System.out.println("Path: +" + url.getPath());
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    for (int i = 0; i < stringz.size(); i++) {
                        if (stringz.get(i).contains(parameters[1])) {
                            temp.add(stringz.get(i));
                        }
                    }
                    return temp.toString();
                }
            }
            return "404 Not Found!";
        }
    }


}

class SearchEngineTest {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new SearchEngine());
    }

}


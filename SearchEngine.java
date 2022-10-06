import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

public class SearchEngine implements URLHandler{
    ArrayList<String> stringz = new ArrayList<String>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return stringz.toString();
        } 
        else if (url.getPath().equals("/add")) {
            stringz.add(url.getQuery());
            System.out.println(url.getPath());
            return String.format("Query added!");
        } 
        else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/search")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    stringz.add(parameters[1]);
                    return parameters[1] + "query has been added";
                }
            }
            return "404 Not Found!";
        }
    }

    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}


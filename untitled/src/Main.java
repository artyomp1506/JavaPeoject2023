import java.awt.print.Book;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        boolean continued = true;
        String searchText;
        while(continued){
            System.out.println("введите книгу для поиска");
            Scanner scanner = new Scanner(System.in);
            searchText = scanner.nextLine();
            continued = searchText.length() > 2;
            System.out.println(searchText);

            WebParser parser = new WebParser();
            MyBook[] books = parser.getWebPage("https://www.chitai-gorod.ru/search?phrase=", searchText);

            for (MyBook book: books)
            {
                System.out.println(book);
            }
        }
    }
}
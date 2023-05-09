import java.awt.print.Book;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

enum BooksAtribute
{
    url,
    lable,
    author,
    price,
    none
}

public class WebParser {
    private String WebPage;
    public static boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }
    private BooksAtribute beforeStringCheck(String string)
    {
        if (string.contains("product-card__text"))
            return BooksAtribute.lable;
        if (string.contains("product-title__author"))
            return BooksAtribute.author;
        if (string.contains("favorite-button"))
            return BooksAtribute.price;
        if (string.contains("href=\"/product/"))
            return BooksAtribute.url;
        return BooksAtribute.none;
    }

    public MyBook[] getWebPage(String _url, String request){
        String page = "";
        boolean fl = false;
        ArrayList<MyBook> books = new ArrayList<MyBook>();

        try {
            _url = (_url+ URLEncoder.encode(request, "UTF-8")+"\n").replace("+","%20");
            System.out.println(_url);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        try {
            URL url = new URL(_url);

            try {
                LineNumberReader reader = new LineNumberReader(new InputStreamReader(url.openStream()));
                String string = reader.readLine();

                String URL = null;
                String lable = null;
                String author = null;
                int price = 0;

                while (string != null) {
                    //page += string + '\n';
                    BooksAtribute attribute = beforeStringCheck(string);
                    //if(attribute == BooksAtribute.url)

                    string = reader.readLine();

                    switch(attribute)
                    {
                        case lable:
                            lable = string.trim();
                            break;
                        case url:
                            break;
                        case price:
                            //System.out.println(string.replace(" ₽", "").replaceAll("\\s+", ""));
                            string = string.replaceAll("\\s+", "").replace("₽", "").replace(" ","");
                            if(isNumeric(string))
                                price = Integer.parseInt(string);
                            break;
                        case author:
                            author = string.trim();
                        default:
                            break;
                    }
                    if (lable!=null && url != null && price!=0 && author!=null)
                    {
                        if(!page.contains("lable-"+lable + "\nprice-" + price + "\nР. author" + author + "\n\n"))
                        {
                            page += "lable-"+lable + "\nprice-" + price + "\nР. author " + author + "\n\n";
                            books.add(new MyBook(lable,
                                    author,
                                    price));
                        }
                        URL = null;
                        lable = null;
                        author = null;
                        price = 0;
                    }
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        return books.toArray(MyBook[]::new);
    }
/*
    public void setWebPage(String _url, String request)
    {
        WebPage = getWebPage(_url, request);
    }

    public String getPage(){
        return WebPage;
    }
/*
    public void parse()
    {
        System.out.println(WebPage.indexOf("product-card__text"));
        char ch = ' ';
        String fin = "";
        int i = WebPage.indexOf("product-card__text");
        for (; ch != '\n'; i++)
        {
            //ch = WebPage.charAt(i);
            System.out.println( i );
            fin += ch;
        }

        //System.out.println(i + ' ' + WebPage.charAt(i));
        i++;
        /*
        for (; ch != '\n'; i++)
        {
            System.out.println( i );
            System.out.println( WebPage.charAt(i));
            fin += WebPage.charAt(i);
        }
        //System.out.println("pp "+fin);
    } */
}

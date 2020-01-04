import java.util.Locale;
import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args)
    {
        String language;
        String country;

        if(args.length!=2)
        {
            language=new String("el");
            country=new String("GR");
        }else
        {
            language=new String(args[0]);
            country=new String(args[1]);
        }

        Locale currentLocale;
        ResourceBundle messages;

        currentLocale=new Locale(language,country);

        messages=ResourceBundle.getBundle("MessagesBundle", currentLocale);
        System.out.println(messages.getString("mainLabelNegative"));

        GUI myGui=new GUI(messages);
    }
}

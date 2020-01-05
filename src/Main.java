import java.util.Locale;
import java.util.ResourceBundle;
import java.text.Normalizer;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Locale currentLocale=Locale.getDefault();
        ResourceBundle messages;
        System.out.println(currentLocale.getLanguage());
        messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);

        System.out.println(messages.getString("mainLabelNegative"));

        GUI myGui=new GUI(messages);
    }
}

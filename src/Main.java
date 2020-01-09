import java.util.Locale;
import java.util.ResourceBundle;
import java.text.Normalizer;
import java.util.Scanner;

public class Main {
    /**
     * In the main method we get the locale so that the language of the game will be determined
     * Also the instance of gui is created
     * @param args
     */
    public static void main(String[] args)
    {
        Locale currentLocale=Locale.getDefault();
        System.out.println(currentLocale.getDisplayLanguage());
        ResourceBundle messages;
        messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);

        System.out.println(messages.getString("mainLabelNegative"));

        GUI myGui=new GUI(messages);
    }
}

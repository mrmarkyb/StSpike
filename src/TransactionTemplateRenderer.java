import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;

import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: mburnett
 * Date: Apr 9, 2010
 * Time: 6:28:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionTemplateRenderer {
    public static void main(String... args) throws FileNotFoundException {
        System.out.println("--------------------");
        System.out.println("html output");
        System.out.println("--------------------");
        System.out.println(renderTempate(getTemplate(htmlBase(), "transactions")));
        System.out.println("--------------------");
        System.out.println("json output");
        System.out.println("--------------------");
        System.out.println(renderTempate(getTemplate(jsonBase(), "transactions")));

    }

    private static String renderTempate(StringTemplate template) {
        template.setAttribute("transactions", Arrays.asList(transactionFor("one"), transactionFor("two")));
        return template.toString();
    }

    private static Object transactionFor(String s) {
        Map<String,String> obj = new HashMap<String,String>();
        obj.put("name", "name for " + s);
        obj.put("time", "time for " + s);
        obj.put("id", "id for " + s);
        return obj;
    }

    private static StringTemplateGroup jsonBase() throws FileNotFoundException {
        return base("json.stg");
    }

    private static StringTemplate getTemplate(StringTemplateGroup base, String templateName) {
        StringTemplateGroup group = new StringTemplateGroup("templates","templates");
        group.setSuperGroup(base);
        return group.getInstanceOf(templateName);
    }

    private static StringTemplateGroup htmlBase() throws FileNotFoundException {
        return base("html.stg");
    }

    private static StringTemplateGroup base(String htmlGroup) throws FileNotFoundException {
        return new StringTemplateGroup(new FileReader(new File("templates", htmlGroup)), DefaultTemplateLexer.class);
    }
}

package classwork.se09.SAX.example1;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class Main {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setValidating(false);
        SAXParser sp = spf.newSAXParser();
        SaxParser handler = new SaxParser();
        sp.parse(Main.class.getResourceAsStream("/web.xml"), handler);
    }
}

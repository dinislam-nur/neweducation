package classwork.se09.SAX.example2;

import java.util.*;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class ListSaxParser extends DefaultHandler {
    private Map<String, String> nameToClass;
    private Map<String, List<String>> nameToPatterns;
    private StringBuilder accumulator;
    private String servletName, servletClass, servletPattern;

    @Override
    public void startDocument() {
        accumulator = new StringBuilder();
        nameToClass = new HashMap<>();
        nameToPatterns = new HashMap<>();
    }

    @Override
    public void characters(char[] buffer, int start, int length) {
        accumulator.append(buffer, start, length);
    }

    @Override
    public void startElement(String namespaceURL, String localName, String qname, Attributes attributes) {
        accumulator.setLength(0);
    }

    @Override
    public void endElement(String namespaceURL, String localName, String qname) {
        switch (localName) {
            case "servlet-name":  // Store servlet name
                servletName = accumulator.toString().trim();
                break;

            case "servlet-class":  // Store servlet class
                servletClass = accumulator.toString().trim();
                break;

            case "url-pattern":  // Store servlet pattern
                servletPattern = accumulator.toString().trim();
                break;

            case "servlet":  // Map name to class
                nameToClass.put(servletName, servletClass);
                break;

            case "servlet-mapping": // Map name to pattern
                nameToPatterns.compute(servletName, (key, value) -> {
                    List<String> result = value == null ? new ArrayList<>() : value;
                    result.add(servletPattern);
                    return result;
                });
                break;
        }
    }

    @Override
    public void endDocument() {
        List<String> servletNames = new ArrayList(nameToClass.keySet());
        Collections.sort(servletNames);
        for (String name : servletNames) {
            String classname = nameToClass.get(name);
            List<String> patterns = nameToPatterns.get(name);
            System.out.println("Servlet: " + name);
            System.out.println("Class: " + classname);
            if (patterns != null) {
                System.out.println("Patterns:");
                for (String pattern : patterns) {
                    System.out.println("\t" + pattern);
                }
            }
            System.out.println();
        }
    }

    @Override
    public void warning(SAXParseException exception) {
        System.err.println("WARNING: line " + exception.getLineNumber() + ": " + exception.getMessage());
    }

    @Override
    public void error(SAXParseException exception) {
        System.err.println("ERROR: line " + exception.getLineNumber() + ": " + exception.getMessage());
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        System.err.println("FATAL: line " + exception.getLineNumber() + ": " + exception.getMessage());
        throw (exception);
    }
}

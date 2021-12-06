package by.petrovich.computer.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidator {
    private static final Logger logger = LogManager.getLogger();

    public XmlValidator() {
    }

    public boolean isFileValid(String xmlFilePath, String xsdFilePath) {
        boolean result = true;
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        try {
            Schema schema = schemaFactory.newSchema(new File(xsdFilePath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlFilePath)));
        } catch (SAXException e) {
            logger.log(Level.ERROR, "Fail with XML file reading: {}", xmlFilePath, e);
            result = false;
        } catch (IOException e) {
            logger.log(Level.ERROR, "XML fail is not valid with XSD scheme: {}", xsdFilePath, xmlFilePath, e);
            result = false;
        }
        logger.log(Level.INFO, "The file is valid ");
        return result;
    }

}

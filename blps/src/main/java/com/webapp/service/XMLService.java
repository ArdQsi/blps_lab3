package com.webapp.service;

import com.webapp.model.UserEntity;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Service;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class XMLService {
    private final XStream xstream;

    public XMLService(){this.xstream = new XStream();}

    public <T> Object getEntity(Class<T> convertClass, String aliasName, String xmlPath) {
        xstream.alias(aliasName, convertClass);
        System.out.println("test2");
        try {
            File file = new File(xmlPath);
            FileReader reader = new FileReader(file);
            if (reader.read() > 0) {
                JAXBContext jaxbContext = JAXBContext.newInstance(convertClass);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                System.out.println("test");
                System.out.println(jaxbUnmarshaller.unmarshal(file));
                return jaxbUnmarshaller.unmarshal(file);
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveEntity(List<?> saveEntity, String xmlPath) {
        xstream.alias("user", UserEntity.class);
        xstream.alias("users", List.class);
        try {
            xstream.toXML(saveEntity, new FileWriter(xmlPath, false));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

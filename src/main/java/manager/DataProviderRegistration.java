package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class DataProviderRegistration {

    @DataProvider
    public Iterator<Object[]> registrationWithFileCSV() throws IOException {
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/newUser.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] data = line.split(",");
            list.add(new Object[]{new User().withFirstName(data[0])
                    .withLastName(i+data[1]).withEmail(i+data[2]).withPassword(data[3])});
            line = reader.readLine();
        }

        return list.iterator();
    }
}

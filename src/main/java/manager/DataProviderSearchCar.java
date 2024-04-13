package manager;

import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderSearchCar {

    @DataProvider
    public Iterator<String> FilData() throws IOException {
        List<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/invaliddtes.csv")));
        String line = reader.readLine();
        while (line !=null) {
            list.add(line);
            line = reader.readLine();
        }

        return list.iterator();
    }
}

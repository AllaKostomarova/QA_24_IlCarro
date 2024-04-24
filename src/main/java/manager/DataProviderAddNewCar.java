package manager;

import models.Car;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderAddNewCar {

    @DataProvider
    public Iterator<Object[]> addNewCarFromFileCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        int i = (int) ((System.currentTimeMillis()/1000)%3600);
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/newCars.csv")));
        String line = reader.readLine();
        while (line !=null){
            String[] car = line.split(",");
            list.add(new Object[]{Car.builder()
                    .location(car[0])
                    .manufacture(car[1])
                    .model(car[2])
                    .year(car[3])
                    .fuel(car[4])
                    .seats(Integer.parseInt(car[5]))
                    .carClass(car[6])
                    .carRegNumber(car[7]+i)
                    .price(Double.parseDouble(car[8]))
                    .about(car[9])
                    .build()});
            line = reader.readLine();
        }
       return list.iterator();

    }
}

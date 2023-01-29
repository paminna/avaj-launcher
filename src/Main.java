import com.company.classes.Aircraft;
import com.company.classes.Writer;
import com.company.error.LauncherException;
import com.company.factory.AircraftFactory;
import com.company.interfaces.Flyable;
import com.company.weather.WeatherTower;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;


public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length == 0){
            System.out.println("No file");
            exit(-1);
        }
        int countRepeat = 0;
        List<Flyable> aircrafts = new ArrayList<>();
        BufferedReader reader = null;
        File file = new File(args[0]);
        if(!file.exists() || file.isDirectory()) {
            System.out.println("No file");
            exit(-1);
        }
        try {
            reader = new BufferedReader(new FileReader(file));
            countRepeat = initAircrafts(reader,  aircrafts, countRepeat);
        } catch (IOException | LauncherException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        startSimulation(countRepeat, aircrafts);
    }

    public static int initAircrafts(BufferedReader reader, List<Flyable> aircrafts, int countRepeat) throws IOException, LauncherException {
        AircraftFactory factory = new AircraftFactory();
        WeatherTower weatherTower = new WeatherTower();
        String[] information;
        String chars = "";
        int c;
        while ((c = reader.read()) != -1) {
            chars += (char) c;
        }
        reader.close();
        information = chars.split("\n");
        countRepeat = Integer.parseInt(information[0]);
        if (countRepeat < 1){
            throw new LauncherException("Can't repeat less than 1 time");
        }
        for (int i = 1; i != information.length; i++){
            String[] settings = information[i].split(" ");
            if (settings.length != 5){
                throw new LauncherException("Can't fly without full information");
            }
            Flyable aircraft = factory.newAircraft(settings[0], settings[1], Integer.parseInt(settings[2]), Integer.parseInt(settings[3]), Integer.parseInt(settings[4]));
            if (aircraft == null){
                throw new LauncherException("Wrong type to create aircraft");
            }
            aircraft.registerTower(weatherTower);
            aircrafts.add(aircraft);
        }
        return countRepeat;
    }

    public static void startSimulation(int countRepeat, List<Flyable> aircrafts) throws IOException {
        for(int i = 0; i < countRepeat; i++){
            for(int it = 0; it != aircrafts.size(); it++) {
                aircrafts.get(it).updateConditions();
            }
            Writer.writeIntoFile("\n END OF ITERATION " + i + "\n");
        }
    }



}

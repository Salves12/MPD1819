package dto;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WeatherWebApi {
    private static final String API_KEY;
    private static final String WEATHER_SERVICE =  "http://api.worldweatheronline.com/premium/v1/";
    private static final String WEATHER_PAST_TEMPLATE =
            "past-weather.ashx?q=%s&date=%s&enddate=%s&tp=24&format=csv&key=%s";
    private static final String WEATHER_SEARCH_TEMPLATE = "search.ashx?query=%s&format=tab&key=%s";

    /**
     * Retrieve API-KEY from resources
     * @return
     */
    private static String getApiKeyFromResources() {
        try {
            URL keyFile = ClassLoader.getSystemResource("worldweatheronline-app-key.txt");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(keyFile.openStream()))) {
                return reader.readLine();
            }

        }
        catch(IOException e) {
            throw new IllegalStateException(
                    "YOU MUST GET a KEY from developer.worldweatheronline.com ad place it in src/main/resources/worldweatheronline-app-key.txt");
        }
    }

    /**
     * Static Constructor
     */
    static {
        API_KEY = getApiKeyFromResources();
    }

    /**
     * Get dto.WeatherInfo's from a GPS local given a date interval
     * @param latitude
     * @param longitude
     * @param from
     * @param to
     * @return
     */
    public Iterable<WeatherInfo> pastWeather( double latitude, double longitude, LocalDate from, LocalDate to) {
        String query = latitude + "," + longitude;
        String path =  WEATHER_SERVICE + String.format(WEATHER_PAST_TEMPLATE, query, from, to, API_KEY);


        List<WeatherInfo> result = new ArrayList<>(); // where the dto.WeatherInfo instances are collected

        try {
           /** BufferedReader br= new BufferedReader( new InputStreamReader(respStream));

            while (br.readLine().startsWith("#"));
            while ((br.readLine())!=null){
                String line =br.readLine();
                result.add(dto.WeatherInfo.valueOf(line));
            }**/

            Iterator<String> iterator=getContentLines(new URL(path).openStream()).iterator();
            while(iterator.hasNext()&&iterator.next().startsWith("#"));
            while(iterator.hasNext()){
                iterator.next();
                result.add(WeatherInfo.valueOf(iterator.next()));
            }
        }
        catch(IOException e) {
            throw new UncheckedIOException(e);
        }
        return result;
    }

    /**
     * Get dto.DayInfo's from a GPS local given a date interval
     * @param latitude
     * @param longitude
     * @param from
     * @param to
     * @return
     */
    public Iterable<DayInfo> pastDays( double latitude, double longitude, LocalDate from, LocalDate to) {
        String query = latitude + "," + longitude;
        String path = WEATHER_SERVICE + String.format(WEATHER_PAST_TEMPLATE, query, from, to, API_KEY);
        List<DayInfo> result = new ArrayList<>(); // where the dto.WeatherInfo instances are collected
        try{
            Iterator<String> iterator=getContentLines(new URL(path).openStream()).iterator();
            while(iterator.hasNext()&&iterator.next().startsWith("#"));
            while(iterator.hasNext()) {
                result.add(DayInfo.valueOf((iterator.next())));
                iterator.next();
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return result;
    }
    /**
     * Get local info given the name of the local
     * @param location
     * @return
     */
    public Iterable<Location> search(String location) {
        List<Location> result=new ArrayList<>();
        String path =  WEATHER_SERVICE + String.format(WEATHER_SEARCH_TEMPLATE, location, API_KEY);
        try {
            Iterator<String> aux = getContentLines(new URL(path).openStream()).iterator();
            String line;
            while (aux.hasNext()){
                line=aux.next();
                if (!line.contains("#")){
                    result.add(Location.valueOf(line));
                }
            }


        }catch (IOException e) {
            throw new UncheckedIOException (e);
        }
        return result;
    }

    private Iterable<String> getContentLines(InputStream input){
        ArrayList i=new ArrayList<String>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(input))) {
            String line;
            while ((line=br.readLine())!=null){
                i.add(line);
            }
        }catch (IOException e){
            throw new UncheckedIOException(e);
        }
        return i;
    }

}

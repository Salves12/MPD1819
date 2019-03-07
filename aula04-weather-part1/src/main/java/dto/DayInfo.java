package dto;/*
   #The day information is available in following format:-
   #date,maxtempC,maxtempF,mintempC,mintempF,sunrise,sunset,moonrise,moonset,moon_phase,moon_illumination
   #2019-01-01,17,63,8,46,07:55 AM,05:26 PM,03:25 AM,02:25 PM,Waning Crescent,34
*/

import java.time.LocalDate;

public class DayInfo {


    // indexes corresponding to fields to use
    private static final int DATE       =   0;
    private static final int MAX_TEMPC  =   1;
    private static final int MIN_TEMPC  =   3;
    private static final int SUNRISE    =   5;
    private static final int SUNSET     =   6;
    private static final int MOONRISE   =   7;
    private static final int MOONSET    =   8;
    private static final int MOON_PHASE =   9;
    private static final int MOON_ILLUM =   10;

    private final LocalDate date;     // index 0
    private final int maxtempC;          // index 1
    private final int mintempC;          // index 3
    private final String sunrise; // index 5
    private final String sunset; // index 6
    private final String moonrise; // index 7
    private final String moonset; // index 8
    private final String  moonphase;   // index 9
    private final int moonillum; //10

    public LocalDate getDate() {
        return date;
    }

    public int getMaxtempC() {
        return maxtempC;
    }

    public int getMintempC() {
        return mintempC;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public String getMoonrise() {
        return moonrise;
    }

    public String getMoonset() {
        return moonset;
    }

    public String getMoonphase() {
        return moonphase;
    }

    public int getMoonillum() {
        return moonillum;
    }

    @Override
    public String toString() {
        return "dto.DayInfo{" +
                "date=" + date +
                ", maxtempC=" + maxtempC +
                ", mintempC=" + mintempC +
                ", sunrise='" + sunrise + '\'' +
                ", sunset='" + sunset + '\'' +
                ", moonrise='" + moonrise + '\'' +
                ", moonset='" + moonset + '\'' +
                ", moonphase='" + moonphase + '\'' +
                ", moonillum=" + moonillum +
                '}';
    }



    public DayInfo(LocalDate date, int maxtempC, int mintempC, String sunrise, String sunset, String moonrise, String moonset, String moonphase, int moonillum) {
        this.date = date;
        this.maxtempC = maxtempC;
        this.mintempC = mintempC;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.moonrise = moonrise;
        this.moonset = moonset;
        this.moonphase = moonphase;
        this.moonillum = moonillum;
    }

    public static DayInfo valueOf(String line) {
        String[] parts = line.split(",");
        return new DayInfo(
                LocalDate.parse(parts[DATE]),
                Integer.parseInt(parts[MAX_TEMPC]),
                Integer.parseInt(parts[MIN_TEMPC]),
                parts[SUNRISE],
                parts[SUNSET],
                parts[MOONRISE],
                parts[MOONSET],
                parts[MOON_PHASE],
                Integer.parseInt(parts[MOON_ILLUM]));
    }






}

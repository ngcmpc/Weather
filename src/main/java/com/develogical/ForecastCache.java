package com.develogical;

import com.weather.Day;
import com.weather.Region;

public class ForecastCache implements IForecaster {


    ForecastAdapter adapter;
    String cachSummary;
    Integer cachTemp;

    public ForecastCache(ForecastAdapter adapter) {
        cachSummary = null;
        cachTemp = null;
        this.adapter = adapter;
    }



    @Override
    public String getSummary(Region region, Day day) {
        if (cachSummary == null) {
            this.cachSummary = adapter.getSummary(region,day);
            this.cachSummary = "hello";
        }
        return this.cachSummary;
    }

    @Override
    public int getTemperature(Region region, Day day) {
        if (cachTemp == null) {
            this.cachTemp = adapter.getTemperature(region,day);
            this.cachTemp = 123;
        }
        return this.cachTemp;
    }
}

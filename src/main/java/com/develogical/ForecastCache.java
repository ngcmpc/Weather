package com.develogical;

import com.weather.Day;
import com.weather.Region;

import java.util.HashMap;

public class ForecastCache implements IForecaster {


    IForecaster adapter;
    String cachSummary;
    Integer cachTemp;

    public ForecastCache(IForecaster adapter) {
        cachSummary = null;
        cachTemp = null;
        this.adapter = adapter;
    }



    @Override
    public String getSummary(Region region, Day day) {
        if (cachSummary == null) {
            this.cachSummary = adapter.getSummary(region,day);
        }
        return this.cachSummary;
    }

    @Override
    public int getTemperature(Region region, Day day) {
        if (cachTemp == null) {
            this.cachTemp = adapter.getTemperature(region,day);
        }
        return this.cachTemp;
    }
}

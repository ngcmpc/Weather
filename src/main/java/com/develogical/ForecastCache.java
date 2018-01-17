package com.develogical;

import com.weather.Day;
import com.weather.Region;

import java.util.HashMap;

public class ForecastCache implements IForecaster {


    IForecaster adapter;
    HashMap<String, String> cachedSummary;
    HashMap<String, Integer> cachedTemperature;

    public ForecastCache(IForecaster adapter) {
        this.cachedSummary = new HashMap<String,String>();
        this.cachedTemperature = new HashMap<String, Integer>();
        this.adapter = adapter;
    }



    @Override
    public String getSummary(Region region, Day day) {
        if (!cachedSummary.containsKey(region.name() + day.name())) {
            this.cachedSummary.put(region.name() + day.name(), adapter.getSummary(region,day));
        }
        return this.cachedSummary.get(region.name() + day.name());
    }

    @Override
    public int getTemperature(Region region, Day day) {
        if (!cachedTemperature.containsKey(region.name() + day.name())) {
            this.cachedTemperature.put(region.name() + day.name(),adapter.getTemperature(region,day));
        }
        return this.cachedTemperature.get(region.name() + day.name());
    }
}

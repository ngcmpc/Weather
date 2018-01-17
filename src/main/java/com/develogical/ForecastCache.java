package com.develogical;

import com.weather.Day;
import com.weather.Region;

import java.util.HashMap;

public class ForecastCache implements IForecaster {

    static interface Timer {
        public boolean timeout();
    }

    static Timer DEFAULT_TIMER = new ForecastCache.Timer(){
        public boolean timeout() {
            return false;
        }
    };

    Timer timer;
    IForecaster adapter;
    HashMap<String, String> cachedSummary;
    HashMap<String, Integer> cachedTemperature;

    public ForecastCache(IForecaster adapter) {
        timer = DEFAULT_TIMER;
        this.cachedSummary = new HashMap<String,String>();
        this.cachedTemperature = new HashMap<String, Integer>();
        this.adapter = adapter;
    }

    public ForecastCache(IForecaster adapter, Timer timer) {
        this.timer = timer;
        this.cachedSummary = new HashMap<String,String>();
        this.cachedTemperature = new HashMap<String, Integer>();
        this.adapter = adapter;
    }


    @Override
    public String getSummary(Region region, Day day) {
        if (cachedSummary.size() > 2 || this.timer.timeout() ) {
            this.cachedSummary = new HashMap<String,String>();
        }
        if (!cachedSummary.containsKey(region.name() + day.name()) ) {
            this.cachedSummary.put(region.name() + day.name(), adapter.getSummary(region,day));
        }
        return this.cachedSummary.get(region.name() + day.name());
    }

    @Override
    public int getTemperature(Region region, Day day) {
        if (cachedTemperature.size() > 2 || this.timer.timeout()) {
            this.cachedTemperature = new HashMap<>();
        }
        if (!cachedTemperature.containsKey(region.name() + day.name())) {
            this.cachedTemperature.put(region.name() + day.name(),adapter.getTemperature(region,day));
        }
        return this.cachedTemperature.get(region.name() + day.name());
    }
}

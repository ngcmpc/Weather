package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;

public class ForecastAdapter implements IForecaster {
    
    private Forecaster forecaster;
    private Forecast forecast;

    public ForecastAdapter(){
        this.forecaster = new Forecaster();
    }


    @Override
    public void setUp(Region region, Day day) {
        this.forecast = forecaster.forecastFor(region, day);
    }

    @Override
    public String getSummary(){
        return this.forecast.summary();
    }

    @Override
    public int getTemperature() {
        return this.forecast.temperature();
    }
}

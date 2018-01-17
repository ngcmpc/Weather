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
    public String getSummary(Region region, Day day){
        this.forecast = this.forecaster.forecastFor(region,day);
        return this.forecast.summary();
    }

    @Override
    public int getTemperature(Region region, Day day) {
        this.forecast = this.forecaster.forecastFor(region,day);
        return this.forecast.temperature();
    }
}

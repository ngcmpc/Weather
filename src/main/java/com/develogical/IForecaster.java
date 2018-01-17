package com.develogical;

import com.weather.Day;
import com.weather.Region;

public interface IForecaster {
    void setUp(Region region, Day day);

    String getSummary();

    int getTemperature();
}

package com.develogical;

import com.weather.Day;
import com.weather.Region;

public interface IForecaster {

    String getSummary(Region region, Day day);

    int getTemperature(Region region, Day day);
}

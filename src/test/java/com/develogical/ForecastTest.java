package com.develogical;

import com.weather.Day;
import com.weather.Region;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ForecastTest {

    @Test
    public void testCachingForecastMechanism() {
        IForecaster forecastCache = new ForecastCache(new ForecastAdapter());

        String summary = forecastCache.getSummary(Region.LONDON, Day.MONDAY);
        int temperature = forecastCache.getTemperature(Region.LONDON, Day.MONDAY);

        assertThat(forecastCache.getSummary(Region.LONDON, Day.MONDAY), is(summary));
        assertThat(forecastCache.getTemperature(Region.LONDON, Day.MONDAY), is(temperature));
    }
}

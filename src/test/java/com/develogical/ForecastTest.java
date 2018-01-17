package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Region;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public class ForecastTest {

    ForecastAdapter fa = mock(ForecastAdapter.class);


    @Test
    public void testCachingForecastMechanism() {
        IForecaster forecastCache = new ForecastCache(fa);

        forecastCache.getSummary(Region.LONDON, Day.MONDAY);
        verify(fa).getSummary(Region.LONDON, Day.MONDAY);
        forecastCache.getTemperature(Region.LONDON, Day.MONDAY);
        verify(fa).getTemperature(Region.LONDON, Day.MONDAY);

        forecastCache.getSummary(Region.LONDON, Day.MONDAY);
        verifyNoMoreInteractions(fa);
        //verify(fa, never()).getSummary(Region.LONDON, Day.MONDAY);
        forecastCache.getTemperature(Region.LONDON, Day.MONDAY);
        verifyNoMoreInteractions(fa);
        //verify(fa, never()).getTemperature(Region.LONDON, Day.MONDAY);
    }
}

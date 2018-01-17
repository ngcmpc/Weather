package com.develogical;

import com.weather.Day;
import com.weather.Region;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ForecastTest {
    @Test
    public void testShouldGetForecastForLondon() {
        IForecaster proxy = new ForecastAdapter();
        proxy.setUp(Region.LONDON, Day.MONDAY);

        assertTrue(proxy.getSummary() != null);
        assertTrue(proxy.getTemperature() != 0);
    }
}

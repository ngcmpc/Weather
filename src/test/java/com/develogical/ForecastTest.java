package com.develogical;

import com.weather.Day;
import com.weather.Region;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public class ForecastTest {

    IForecaster delegate = mock(IForecaster.class);


    @Test
    public void testCachingForecastMechanism() {
        IForecaster forecastCache = new ForecastCache(delegate);

        when(delegate.getSummary(Region.LONDON, Day.MONDAY)).thenReturn("fine");
        when(delegate.getTemperature(Region.LONDON, Day.MONDAY)).thenReturn(123456);

        String summary = forecastCache.getSummary(Region.LONDON, Day.MONDAY);
        assertThat(summary,equalTo("fine"));
        verify(delegate).getSummary(Region.LONDON, Day.MONDAY);
        int temperature = forecastCache.getTemperature(Region.LONDON, Day.MONDAY);
        assertThat(temperature,equalTo(123456));
        verify(delegate).getTemperature(Region.LONDON, Day.MONDAY);

        forecastCache.getSummary(Region.LONDON, Day.MONDAY);
        verifyNoMoreInteractions(delegate);
        //verify(delegate, never()).getSummary(Region.LONDON, Day.MONDAY);
        forecastCache.getTemperature(Region.LONDON, Day.MONDAY);
        verifyNoMoreInteractions(delegate);
    }

    @Test
    public void testCacheingForecastWhenCallingDifferentCountry() {
        IForecaster forecastCache = new ForecastCache(delegate);
        when(delegate.getSummary(Region.LONDON, Day.MONDAY)).thenReturn("fine");
        when(delegate.getTemperature(Region.LONDON, Day.MONDAY)).thenReturn(4);
        when(delegate.getSummary(Region.EDINBURGH, Day.MONDAY)).thenReturn("notfine");
        when(delegate.getTemperature(Region.EDINBURGH, Day.MONDAY)).thenReturn(3);

        String summary = forecastCache.getSummary(Region.LONDON, Day.MONDAY);
        assertThat(summary,equalTo("fine"));
        verify(delegate).getSummary(Region.LONDON, Day.MONDAY);
        int temperature = forecastCache.getTemperature(Region.LONDON, Day.MONDAY);
        assertThat(temperature,equalTo(4));
        verify(delegate).getTemperature(Region.LONDON, Day.MONDAY);

        String summaryEdinburgh = forecastCache.getSummary(Region.EDINBURGH, Day.MONDAY);
        assertThat(summaryEdinburgh,equalTo("notfine"));
        verify(delegate).getSummary(Region.EDINBURGH, Day.MONDAY);
        int temperatureEdinburgh = forecastCache.getTemperature(Region.EDINBURGH, Day.MONDAY);
        assertThat(temperatureEdinburgh,equalTo(3));
        verify(delegate).getTemperature(Region.EDINBURGH, Day.MONDAY);
    }

    @Test
    public void testCanCacheBasedOnDifferentDate() {
        IForecaster forecastCache = new ForecastCache(delegate);
        when(delegate.getSummary(Region.LONDON, Day.MONDAY)).thenReturn("fine");
        when(delegate.getTemperature(Region.LONDON, Day.MONDAY)).thenReturn(4);
        when(delegate.getSummary(Region.LONDON, Day.TUESDAY)).thenReturn("notfine");
        when(delegate.getTemperature(Region.LONDON, Day.TUESDAY)).thenReturn(3);

        String summary = forecastCache.getSummary(Region.LONDON, Day.MONDAY);
        assertThat(summary,equalTo("fine"));
        verify(delegate).getSummary(Region.LONDON, Day.MONDAY);
        int temperature = forecastCache.getTemperature(Region.LONDON, Day.MONDAY);
        assertThat(temperature,equalTo(4));
        verify(delegate).getTemperature(Region.LONDON, Day.MONDAY);

        String summaryEdinburgh = forecastCache.getSummary(Region.LONDON, Day.TUESDAY);
        assertThat(summaryEdinburgh,equalTo("notfine"));
        verify(delegate).getSummary(Region.LONDON, Day.TUESDAY);
        int temperatureEdinburgh = forecastCache.getTemperature(Region.LONDON, Day.TUESDAY);
        assertThat(temperatureEdinburgh,equalTo(3));
        verify(delegate).getTemperature(Region.LONDON, Day.TUESDAY);
    }
}

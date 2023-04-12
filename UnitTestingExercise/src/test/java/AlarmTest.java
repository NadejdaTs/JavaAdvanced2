import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import p06_TirePressureMonitoringSystem.Alarm;
import p06_TirePressureMonitoringSystem.Sensor;

import static org.mockito.Mockito.when;

public class AlarmTest {

    // sensor.popNextPressurePsiValue() < 17 || > 21 -> alarm is true

    @Test
    public void testAlarmWithLowerValue(){
        //always pressure to be < 17
        //Sensor sensor = new Sensor();
        Sensor sensor = Mockito.mock(Sensor.class);
        when(sensor.popNextPressurePsiValue()).thenReturn(16.0);
        Alarm alarm = new Alarm(sensor);
        alarm.check();
        Assert.assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void testAlarmWithHigherValue(){
        //always pressure to be > 21
        Sensor sensor = Mockito.mock(Sensor.class);
        when(sensor.popNextPressurePsiValue()).thenReturn(21.1);
        Alarm alarm = new Alarm(sensor);
        alarm.check();
        Assert.assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void testAlarmIsNormalOff(){
        Sensor sensor = Mockito.mock(Sensor.class);
        when(sensor.popNextPressurePsiValue()).thenReturn(17.01);
        Alarm alarm = new Alarm(sensor);
        alarm.check();
        Assert.assertFalse(alarm.getAlarmOn());
    }
}

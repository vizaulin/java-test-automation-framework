package project.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SleepUtils {
    private static final Logger log = LoggerFactory.getLogger(SleepUtils.class);

    public static void sleepSeconds(long seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }
}

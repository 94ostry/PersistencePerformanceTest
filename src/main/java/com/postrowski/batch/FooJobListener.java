package com.postrowski.batch;

import javax.batch.api.listener.JobListener;
import javax.inject.Named;
import java.time.Duration;
import java.time.LocalTime;

/**
 * Created by postrowski on 2016-08-04.
 */
@Named
public class FooJobListener implements JobListener
{
    private long startTime;

    @Override
    public void beforeJob() throws Exception
    {
        startTime = System.currentTimeMillis();
        System.out.println(" ============================================== > JOB start " + LocalTime.now());
    }

    @Override
    public void afterJob() throws Exception
    {
        long stopTime = System.currentTimeMillis();
        final Duration duration = Duration.ofMillis( stopTime - startTime );
        System.out.println(" ============================================== > JOB stop " + LocalTime.now() + "  " + duration);

    }
}

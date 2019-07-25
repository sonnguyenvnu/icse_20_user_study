package com.hope.quartz.schedule;

import com.hope.quartz.job.TestJob1;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author:aodeng(低调�?熊猫)
 * @blog:（https://aodeng.cc)
 * @Description: TODO
 * @Date: 19-5-15
 **/
@Component
public class CronScheduleJob {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    /** 
    * @Description: �?�动定时任务
    * @Param: []
    * @return: []
    * @Author: aodeng
    * @Date: 19-5-15
    */ 
    public void scheduleJobsRun() throws SchedulerException{
        Scheduler scheduler=schedulerFactoryBean.getScheduler();
        schedulejobs(scheduler);
    }

    /** 
    * @Description: 构建 jobDetail�?CronTrigger
    * @Param: [scheduler]
    * @return: [scheduler]
    * @Author: aodeng
    * @Date: 19-5-15
    */ 
    private void schedulejobs(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(TestJob1.class).withIdentity("job","group").build();
        //间隔 6 秒
        CronScheduleBuilder scheduleBuilder=CronScheduleBuilder.cronSchedule("0/6 * * * * ?");
        CronTrigger cronTrigger= TriggerBuilder.newTrigger().withIdentity("trigger","group").withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }
}

package org.nutz.lang;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.nutz.lang.util.CronSequenceGenerator;
import org.nutz.log.Log;
import org.nutz.log.Logs;

/**
 * 定时任务�?务的�?�好�?装
 * @author QinerG(qinerg@gmail.com)
 */
public abstract class Tasks {

    private static Log logger = Logs.get();

    private static ScheduledThreadPoolExecutor taskScheduler = new ScheduledThreadPoolExecutor(getBestPoolSize());
    private static List<Timer> timerList = new ArrayList<Timer>();
    
    /**
     * 通过 cron 表达�?�?��?置任务的�?�动时间
     * @param task
     * @param cronExpression
     */
    public static void scheduleAtCron(final Runnable task, String cronExpression) {
        TimeSchedule timeSchedule = new TimeSchedule(task, cronExpression);
        timeSchedule.start();
    }

    /**
     * 立�?��?�动，并以固定的频率�?��?行任务。�?�续任务的�?�动时间�?�?��?次任务延时影�?。
     * @param task 具体待执行的任务
     * @param periodSeconds �?次执行任务的间隔时间(�?��?秒)
     */
    public static ScheduledFuture<?> scheduleAtFixedRate(Runnable task, long periodSeconds) {
        return scheduleAtFixedRate(task, 0, periodSeconds, TimeUnit.SECONDS);
    }

    /**
     * 在指定的延时之�?�开始以固定的频率�?��?行任务。�?�续任务的�?�动时间�?�?��?次任务延时影�?。
     * @param task 具体待执行的任务
     * @param initialDelay 首次执行任务的延时时间
     * @param periodSeconds �?次执行任务的间隔时间(�?��?秒)
     * @param unit 时间�?��?
     */
    public static ScheduledFuture<?> scheduleAtFixedRate(Runnable task, long initialDelay, long periodSeconds, TimeUnit unit) {
        return taskScheduler.scheduleAtFixedRate(task, initialDelay, periodSeconds, unit);
    }

    /**
     * 在指定的时间点开始以固定的频率�?行任务。�?�续任务的�?�动时间�?�?��?次任务延时影�?。
     * @param task 具体待执行的任务
     * @param startTime 首次�?行的时间点,支�? "yyyy-MM-dd HH:mm:ss" 格�?
     * @param period �?次执行任务的间隔时间
     * @param unit 时间�?��?
     */
    public static void scheduleAtFixedRate(Runnable task, String startTime, long period, TimeUnit unit) throws ParseException {
        Date dt = Times.D(startTime);
        scheduleAtFixedRate(task, dt, period, unit);
    }

    /**
     * 在指定的时间点开始以固定的频率�?行任务。�?�续任务的�?�动时间�?�?��?次任务延时影�?。
     * @param task 具体待执行的任务
     * @param startTime 首次�?行的时间点
     * @param period �?次执行任务的间隔时间
     * @param unit 时间�?��?
     */
    public static void scheduleAtFixedRate(final Runnable task, Date startTime, final long period, final TimeUnit unit) {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                taskScheduler.scheduleAtFixedRate(task, 0, period, unit);
                timer.cancel();
                timerList.remove(timer);
            }
        }, startTime);
        timerList.add(timer);
    }

    /**
     * 立�?��?�动，两次任务间�?�?固定的时间间隔
     * @param task 具体待执行的任务
     * @param periodSeconds 两次任务的间隔时间(�?��?秒)
     */
    public static ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, long periodSeconds) {
        return scheduleWithFixedDelay(task, 0, periodSeconds, TimeUnit.SECONDS);
    }

    /**
     * 在指定的延时之�?��?�动，两次任务间�?�?固定的时间间隔
     * @param task 具体待执行的任务
     * @param initialDelay 首次执行任务的延时时间
     * @param period 两次任务的间隔时间(�?��?秒)
     * @param unit 时间�?��?
     */
    public static ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, long initialDelay, long period, TimeUnit unit) {
        return taskScheduler.scheduleWithFixedDelay(task, initialDelay, period, unit);
    }

    /**
     * 在指定的时间点�?�动，两次任务间�?�?固定的时间间隔
     * @param task 具体待执行的任务
     * @param startTime 首次�?行的时间点,支�? "yyyy-MM-dd HH:mm:ss" 格�?
     * @param period 两次任务的间隔时间
     * @param unit 时间�?��?
     */
    public static void scheduleWithFixedDelay(Runnable task, String startTime, long period, TimeUnit unit) throws ParseException {
        Date dt = Times.D(startTime);
        scheduleWithFixedDelay(task, dt, period, unit);
    }

    /**
     * 在指定的时间点�?�动，两次任务间�?�?固定的时间间隔
     * @param task 具体待执行的任务
     * @param startTime 首次�?行的时间点
     * @param period 两次任务的间隔时间
     * @param unit 时间�?��?
     */
    public static void scheduleWithFixedDelay(final Runnable task, Date startTime, final long period, final TimeUnit unit) {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                taskScheduler.scheduleWithFixedDelay(task, 0, period, unit);
                timer.cancel();
                timerList.remove(timer);
            }
        }, startTime);
        timerList.add(timer);
    }
    /**
     * 在指定的时间点�?�动任务�?��?行一次
     * @param task 具体待执行的任务
     * @param startTime �?行的时间点
     */
    public static void scheduleAtFixedTime(final Runnable task, Date startTime) {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                taskScheduler.execute(task);
                timer.cancel();
                timerList.remove(timer);
            }
        }, startTime);
        timerList.add(timer);
    }
    /**
     * 在符�?��?�件的时间点�?�动任务
     * @see scheduleAtCron
     * @param task 具体待执行的任务
     * @param expression  cron表达�?
     */
    @Deprecated
    public static void scheduleAtFixedTime(final Runnable task, String cronExpression) {
    	scheduleAtCron(task, cronExpression);
    }

    /**
     * 调整线程池大�?
     * @param threadPoolSize 线程池大�?
     */
    public static void resizeThreadPool(int threadPoolSize) {
        taskScheduler.setCorePoolSize(threadPoolSize);
    }

    /**
     * 返回定时任务线程池，�?��?�更高级的应用
     * @return 当�?的线程池
     */
    public static ScheduledThreadPoolExecutor getTaskScheduler() {
        return taskScheduler;
    }

    /**
     * 关闭定时任务�?务
     * <p>系统关闭时�?�调用此方法终止正在执行的定时任务，一旦关闭�?��?�?许�?�?�线程池中添加任务，�?�则会报RejectedExecutionException异常</p>
     */
    public static void depose() {
    	int timerNum = timerList.size();
    	//清除Timer
    	synchronized (timerList) {
    		for (Timer t: timerList)
    			t.cancel();
    		timerList.clear();
    	}
    	
        List<Runnable> awaitingExecution = taskScheduler.shutdownNow();
        logger.infof("Tasks stopping. Tasks awaiting execution: %d", timerNum + awaitingExecution.size());
    }

    /**
     * �?�?�动定时任务�?务
     */
    public static void reset() {
        depose();
        taskScheduler = new ScheduledThreadPoolExecutor(getBestPoolSize());
    }

    /**
     * 根�?� Java 虚拟机�?�用处�?�器数目返回最佳的线程数。<br>
     * 最佳的线程数 = CPU�?�用核心数 / (1 - 阻塞系数)，其中阻塞系数这里设为0.9
     */
    private static int getBestPoolSize() {
        try {
            // JVM�?�用处�?�器的个数
            final int cores = Runtime.getRuntime().availableProcessors();
            // 最佳的线程数 = CPU�?�用核心数 / (1 - 阻塞系数)
            // TODO 阻塞系数是�?是需�?有个setter方法能让使用者自由设置呢？
            return (int)(cores / (1 - 0.9));
        }
        catch (Throwable e) {
            // 异常�?�生时姑且返回10个任务线程池
            return 10;
        }
    }
}

class TimeSchedule implements Runnable {
    private final Runnable task;
    private final CronSequenceGenerator cron;

    public TimeSchedule(Runnable task, String expression) {
        this.task = task;
        this.cron =  new CronSequenceGenerator(expression);
    }

    public void start(){
        Date startTime = cron.next(new Date());
        Tasks.scheduleAtFixedTime(this,startTime);
    }

    @Override
    public void run() {
        task.run();
        start();
    }
}

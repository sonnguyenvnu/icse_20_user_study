package com.vondear.rxtool;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author vondear
 * @date 2016/1/24
 *  线程池相关工具类
 */
public class RxThreadPoolTool {

    private ExecutorService exec;
    private ScheduledExecutorService scheduleExec;
    /**
     * ThreadPoolUtils构造函数
     *
     * @param type         线程池类型
     * @param corePoolSize �?�对Fixed和Scheduled线程池起效
     */
    public RxThreadPoolTool(Type type, int corePoolSize) {
        // 构造有定时功能的线程池
        // ThreadPoolExecutor(corePoolSize, Integer.MAX_VALUE, 10L, TimeUnit.MILLISECONDS, new BlockingQueue<Runnable>)
        scheduleExec = Executors.newScheduledThreadPool(corePoolSize);
        switch (type) {
            case FixedThread:
                // 构造一个固定线程数目的线程池
                // ThreadPoolExecutor(corePoolSize, corePoolSize, 0L, TimeUnit.MILLISECONDS, new
                // LinkedBlockingQueue<Runnable>());
                exec = Executors.newFixedThreadPool(corePoolSize);
                break;
            case SingleThread:
                // 构造一个�?�支�?一个线程的线程池,相当于newFixedThreadPool(1)
                // ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>())
                exec = Executors.newSingleThreadExecutor();
                break;
            case CachedThread:
                // 构造一个缓冲功能的线程池
                // ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
                exec = Executors.newCachedThreadPool();
                break;
            default:
                exec = scheduleExec;
                break;
        }
    }

    /**
     * 在未�?��?个时间执行给定的命令
     * <p>该命令�?�能在新的线程�?已入池的线程或者正调用的线程中执行，这由 Executor 实现决定。</p>
     *
     * @param command 命令
     */
    public void execute(Runnable command) {
        exec.execute(command);
    }

    /**
     * 在未�?��?个时间执行给定的命令链表
     * <p>该命令�?�能在新的线程�?已入池的线程或者正调用的线程中执行，这由 Executor 实现决定。</p>
     *
     * @param commands 命令链表
     */
    public void execute(List<Runnable> commands) {
        for (Runnable command : commands) {
            exec.execute(command);
        }
    }

    /**
     * 待以�?�??交的任务执行完毕�?�关闭线程池
     * <p>�?�动一次顺�?关闭，执行以�?�??交的任务，但�?接�?�新任务。
     * 如果已�?关闭，则调用没有作用。</p>
     */
    public void shutDown() {
        exec.shutdown();
    }

    /**
     * 试图�?�止所有正在执行的活动任务
     * <p>试图�?�止所有正在执行的活动任务，暂�?�处�?�正在等待的任务，并返回等待执行的任务列表。</p>
     * <p>无法�?�?能够�?�止正在处�?�的活动执行任务，但是会尽力�?试。</p>
     *
     * @return 等待执行的任务的列表
     */
    public List<Runnable> shutDownNow() {
        return exec.shutdownNow();
    }

    /**
     * 判断线程池是�?�已关闭
     *
     * @return {@code true}: 是<br>{@code false}: �?�
     */
    public boolean isShutDown() {
        return exec.isShutdown();
    }

    /**
     * 关闭线程池�?�判断所有任务是�?�都已完�?
     * <p>注�?，除�?�首先调用 shutdown 或 shutdownNow，�?�则 isTerminated 永�?为 true。</p>
     *
     * @return {@code true}: 是<br>{@code false}: �?�
     */
    public boolean isTerminated() {
        return exec.isTerminated();
    }

    /**
     * 请求关闭�?�?�生超时或者当�?线程中断
     * <p>无论哪一个首先�?�生之�?�，都将导致阻塞，直到所有任务完�?执行。</p>
     *
     * @param timeout 最长等待时间
     * @param unit    时间�?��?
     * @return {@code true}: 请求�?功<br>{@code false}: 请求超时
     * @throws InterruptedException 终端异常
     */
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return exec.awaitTermination(timeout, unit);
    }

    /**
     * �??交一个Callable任务用于执行
     * <p>如果想立�?�阻塞任务的等待，则�?�以使用{@code result = exec.submit(aCallable).get();}形�?的构造。</p>
     *
     * @param task 任务
     * @param <T>  泛型
     * @return 表示任务等待完�?的Future, 该Future的{@code get}方法在�?功完�?时将会返回该任务的结果。
     */
    public <T> Future<T> submit(Callable<T> task) {
        return exec.submit(task);
    }

    /**
     * �??交一个Runnable任务用于执行
     *
     * @param task   任务
     * @param result 返回的结果
     * @param <T>    泛型
     * @return 表示任务等待完�?的Future, 该Future的{@code get}方法在�?功完�?时将会返回该任务的结果。
     */
    public <T> Future<T> submit(Runnable task, T result) {
        return exec.submit(task, result);
    }

    /**
     * �??交一个Runnable任务用于执行
     *
     * @param task 任务
     * @return 表示任务等待完�?的Future, 该Future的{@code get}方法在�?功完�?时将会返回null结果。
     */
    public Future<?> submit(Runnable task) {
        return exec.submit(task);
    }

    /**
     * 执行给定的任务
     * <p>当所有任务完�?时，返回�?�?任务状�?和结果的Future列表。
     * 返回列表的所有元素的{@link Future#isDone}为{@code true}。
     * 注�?，�?�以正常地或通过抛出异常�?�终止已完�?任务。
     * 如果正在进行此�?作时修改了给定的 collection，则此方法的结果是�?确定的。</p>
     *
     * @param tasks 任务集�?�
     * @param <T>   泛型
     * @return 表示任务的 Future 列表，列表顺�?与给定任务列表的迭代器所生�?的顺�?相�?�，�?个任务都已完�?。
     * @throws InterruptedException 如果等待时�?�生中断，在这�?情况下�?�消尚未完�?的任务。
     */
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return exec.invokeAll(tasks);
    }

    /**
     * 执行给定的任务
     * <p>当所有任务完�?或超时期满时(无论哪个首先�?�生)，返回�?�?任务状�?和结果的Future列表。
     * 返回列表的所有元素的{@link Future#isDone}为{@code true}。
     * 一旦返回�?�，�?��?�消尚未完�?的任务。
     * 注�?，�?�以正常地或通过抛出异常�?�终止已完�?任务。
     * 如果此�?作正在进行时修改了给定的 collection，则此方法的结果是�?确定的。</p>
     *
     * @param tasks   任务集�?�
     * @param timeout 最长等待时间
     * @param unit    时间�?��?
     * @param <T>     泛型
     * @return 表示任务的 Future 列表，列表顺�?与给定任务列表的迭代器所生�?的顺�?相�?�。如果�?作未超时，则已完�?所有任务。如果确实超时了，则�?些任务尚未完�?。
     * @throws InterruptedException 如果等待时�?�生中断，在这�?情况下�?�消尚未完�?的任务
     */
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws
            InterruptedException {
        return exec.invokeAll(tasks, timeout, unit);
    }

    /**
     * 执行给定的任务
     * <p>如果�?个任务已�?功完�?（也就是未抛出异常），则返回其结果。
     * 一旦正常或异常返回�?�，则�?�消尚未完�?的任务。
     * 如果此�?作正在进行时修改了给定的collection，则此方法的结果是�?确定的。</p>
     *
     * @param tasks 任务集�?�
     * @param <T>   泛型
     * @return �?个任务返回的结果
     * @throws InterruptedException 如果等待时�?�生中断
     * @throws ExecutionException   如果没有任务�?功完�?
     */
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        return exec.invokeAny(tasks);
    }

    /**
     * 执行给定的任务
     * <p>如果在给定的超时期满�?�?个任务已�?功完�?（也就是未抛出异常），则返回其结果。
     * 一旦正常或异常返回�?�，则�?�消尚未完�?的任务。
     * 如果此�?作正在进行时修改了给定的collection，则此方法的结果是�?确定的。</p>
     *
     * @param tasks   任务集�?�
     * @param timeout 最长等待时间
     * @param unit    时间�?��?
     * @param <T>     泛型
     * @return �?个任务返回的结果
     * @throws InterruptedException 如果等待时�?�生中断
     * @throws ExecutionException   如果没有任务�?功完�?
     * @throws TimeoutException     如果在所有任务�?功完�?之�?给定的超时期满
     */
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws
            InterruptedException, ExecutionException, TimeoutException {
        return exec.invokeAny(tasks, timeout, unit);
    }

    /**
     * 延迟执行Runnable命令
     *
     * @param command 命令
     * @param delay   延迟时间
     * @param unit    �?��?
     * @return 表示挂起任务完�?的ScheduledFuture，并且其{@code get()}方法在完�?�?�将返回{@code null}
     */
    public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
        return scheduleExec.schedule(command, delay, unit);
    }

    /**
     * 延迟执行Callable命令
     *
     * @param callable 命令
     * @param delay    延迟时间
     * @param unit     时间�?��?
     * @param <V>      泛型
     * @return �?�用于�??�?�结果或�?�消的ScheduledFuture
     */
    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
        return scheduleExec.schedule(callable, delay, unit);
    }

    /**
     * 延迟并循环执行命令
     *
     * @param command      命令
     * @param initialDelay 首次执行的延迟时间
     * @param period       连续执行之间的周期
     * @param unit         时间�?��?
     * @return 表示挂起任务完�?的ScheduledFuture，并且其{@code get()}方法在�?�消�?�将抛出异常
     */
    public ScheduledFuture<?> scheduleWithFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        return scheduleExec.scheduleAtFixedRate(command, initialDelay, period, unit);
    }

    /**
     * 延迟并以固定休�?�时间循环执行命令
     *
     * @param command      命令
     * @param initialDelay 首次执行的延迟时间
     * @param delay        �?一次执行终止和下一次执行开始之间的延迟
     * @param unit         时间�?��?
     * @return 表示挂起任务完�?的ScheduledFuture，并且其{@code get()}方法在�?�消�?�将抛出异常
     */
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
        return scheduleExec.scheduleWithFixedDelay(command, initialDelay, delay, unit);
    }

    public enum Type {
        FixedThread,
        CachedThread,
        SingleThread,
    }
}

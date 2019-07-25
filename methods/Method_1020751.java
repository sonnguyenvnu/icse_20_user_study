/** 
 * Inserts a time delay between emissions from the upstream source, including an initial delay. <dl> <dt><b>Backpressure:</b></dt> <dd>The operator itself doesn't interfere with backpressure and uses an unbounded internal buffer to store elements that need delay.</dd> <dt><b>Scheduler:</b></dt> <dd>The operator uses the computation  {@link Scheduler}.</dd> </dl>
 * @param < T > the value type
 * @param initialDelay the initial delay
 * @param betweenDelay the (minimum) delay time between elements
 * @param unit the time unit of the initial delay and the between delay values
 * @param delayError delay the onError event from upstream
 * @return the new FlowableTransformer instance
 * @since 0.9.0
 */
@BackpressureSupport(BackpressureKind.PASS_THROUGH) @SchedulerSupport(SchedulerSupport.COMPUTATION) public static <T>FlowableTransformer<T,T> spanout(long initialDelay,long betweenDelay,TimeUnit unit,boolean delayError){
  return spanout(initialDelay,betweenDelay,unit,Schedulers.computation(),delayError);
}

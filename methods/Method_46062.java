/** 
 * Record the number of calls and time consuming.
 * @param mixinMetric MixinMetric
 * @param model       information model
 */
private void recordCounterAndTimer(MixinMetric mixinMetric,RpcAbstractLookoutModel model){
  Counter totalCounter=mixinMetric.counter("total_count");
  Timer totalTimer=mixinMetric.timer("total_time");
  Long elapsedTime=model.getElapsedTime();
  totalCounter.inc();
  if (elapsedTime != null) {
    totalTimer.record(elapsedTime,TimeUnit.MILLISECONDS);
  }
  if (!model.getSuccess()) {
    Counter failCounter=mixinMetric.counter("fail_count");
    Timer failTimer=mixinMetric.timer("fail_time");
    failCounter.inc();
    if (elapsedTime != null) {
      failTimer.record(elapsedTime,TimeUnit.MILLISECONDS);
    }
  }
}

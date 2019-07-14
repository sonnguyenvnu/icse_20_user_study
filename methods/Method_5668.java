/** 
 * Called from  {@link #evaluateQueueSize(long,List)} to determine whether an evaluation should beperformed.
 * @param nowMs The current value of {@link Clock#elapsedRealtime()}.
 * @return Whether an evaluation should be performed.
 */
protected boolean shouldEvaluateQueueSize(long nowMs){
  return lastBufferEvaluationMs == C.TIME_UNSET || nowMs - lastBufferEvaluationMs >= minTimeBetweenBufferReevaluationMs;
}

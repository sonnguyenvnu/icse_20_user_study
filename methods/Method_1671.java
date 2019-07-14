protected void maybeHandleIntermediateResult(PooledByteBufferOutputStream pooledOutputStream,FetchState fetchState){
  final long nowMs=SystemClock.uptimeMillis();
  if (shouldPropagateIntermediateResults(fetchState) && nowMs - fetchState.getLastIntermediateResultTimeMs() >= TIME_BETWEEN_PARTIAL_RESULTS_MS) {
    fetchState.setLastIntermediateResultTimeMs(nowMs);
    fetchState.getListener().onProducerEvent(fetchState.getId(),PRODUCER_NAME,INTERMEDIATE_RESULT_PRODUCER_EVENT);
    notifyConsumer(pooledOutputStream,fetchState.getOnNewResultStatusFlags(),fetchState.getResponseBytesRange(),fetchState.getConsumer());
  }
}

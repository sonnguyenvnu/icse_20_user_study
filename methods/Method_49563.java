private long elapsedNanos(){
  return isRunning ? ticker.read() - startTick + elapsedNanos : elapsedNanos;
}

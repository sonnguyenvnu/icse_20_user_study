@Override public void close() throws Exception {
  if (isTimerRunning) {
    profiler.stopTimer();
  }
}

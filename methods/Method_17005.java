@Benchmark public void expire(ThreadState threadState){
  long time=times[threadState.index++ & MASK];
  timer.setVariableTime(time);
  timerWheel.nanos=(time - DELTA);
  timerWheel.advance(time);
  timerWheel.schedule(timer);
}

@Benchmark public void reschedule(ThreadState threadState){
  timer.setVariableTime(times[threadState.index++ & MASK]);
  timerWheel.reschedule(timer);
}

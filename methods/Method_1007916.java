public TimeInstrument stop(){
  stopLastTimeInstrument();
  globalStopWatch.stop();
  return this;
}

public boolean inRange(SketchInterval interval){
  return interval != SketchInterval.BEFORE_START && interval.stopPdeOffset < pdeCode.length();
}

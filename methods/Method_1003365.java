@Override public Value subtract(Value v){
  return IntervalUtils.intervalFromAbsolute(getQualifier(),IntervalUtils.intervalToAbsolute(this).subtract(IntervalUtils.intervalToAbsolute((ValueInterval)v)));
}

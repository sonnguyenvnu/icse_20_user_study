@Override protected float calculateValue(long frameTimeNanos){
  final float initialValue=getInput(INITIAL_INPUT).getValue();
  final float endValue=getInput(END_INPUT).getValue();
  final float fractionValue=getInput(DEFAULT_INPUT).getValue();
  final float valRange=endValue - initialValue;
  return initialValue + fractionValue * valRange;
}

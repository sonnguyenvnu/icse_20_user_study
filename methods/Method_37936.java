protected boolean isLastIteration(final int value){
  return step > 0 ? value > end : value < end;
}

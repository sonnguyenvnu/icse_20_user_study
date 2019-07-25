@Override public boolean process(T problem){
  if (problem.getSeverity() == MessageStatus.ERROR) {
    myErrors.add(formatMessage(problem));
  }
 else   if (myCollectWarnings) {
    myWarnings.add(formatMessage(problem));
  }
  return true;
}

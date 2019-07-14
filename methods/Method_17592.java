@Override public void finished(){
  policyStats.setName(getPolicyName());
  printSegmentSizes();
  long actualWindowSize=data.values().stream().filter(n -> n.queue == WINDOW).count();
  long actualProbationSize=data.values().stream().filter(n -> n.queue == PROBATION).count();
  long actualProtectedSize=data.values().stream().filter(n -> n.queue == PROTECTED).count();
  long calculatedProbationSize=data.size() - actualWindowSize - actualProtectedSize;
  checkState((long)windowSize == actualWindowSize,"Window: %s != %s",(long)windowSize,actualWindowSize);
  checkState((long)protectedSize == actualProtectedSize,"Protected: %s != %s",(long)protectedSize,actualProtectedSize);
  checkState(actualProbationSize == calculatedProbationSize,"Probation: %s != %s",actualProbationSize,calculatedProbationSize);
  checkState(data.size() <= maximumSize,"Maximum: %s > %s",data.size(),maximumSize);
}

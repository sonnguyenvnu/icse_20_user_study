@Override public final void start(@NotNull String taskName,int totalWork){
  if (myTotal >= 0) {
    throw new IllegalStateException("start() is called twice");
  }
  myActiveChild=null;
  myDone=0;
  assert totalWork >= 0 : "totalWork=" + totalWork;
  myTotal=totalWork;
  myName=taskName;
  setTitleInternal(taskName);
  setStepInternal("");
  startInternal(taskName);
  update();
}

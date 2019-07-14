protected void addFailure(Throwable e){
  notifier.fireTestFailure(new Failure(description,e));
}

protected void addFailure(Throwable targetException){
  notifier.fireTestFailure(new Failure(description,targetException));
}

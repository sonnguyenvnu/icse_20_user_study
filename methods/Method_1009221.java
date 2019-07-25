@Override public void run(@NotNull ProgressIndicator indicator){
  indicator.setIndeterminate(true);
  try {
    create(errorBean);
    successCallback.consume(true);
  }
 catch (  Exception exception) {
    errorCallback.consume(exception);
  }
}

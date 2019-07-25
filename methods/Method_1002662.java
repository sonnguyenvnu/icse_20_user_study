private void fail(Throwable cause){
  state=State.DONE;
  logBuilder.endRequest(cause);
  logBuilder.endResponse(cause);
  cancelSubscription();
}

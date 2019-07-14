private void showErrors(Throwable exception){
  showErrors(Collections.singletonList(ErrorCause.fromThrowable(exception)));
}

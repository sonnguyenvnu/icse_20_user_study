@Override protected void onDismissedByScrolling(){
  if (listener != null)   listener.onDismissed();
  super.onDismissedByScrolling();
}

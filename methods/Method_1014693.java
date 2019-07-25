public void stop(){
  getShowTextView().setEnabled(true);
  getShowTextView().setText(defaultText);
  handler.removeCallbacks(this);
  if (countdownListener != null) {
    countdownListener.onFinish();
  }
  running=false;
}

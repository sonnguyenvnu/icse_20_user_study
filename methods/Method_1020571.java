public void stop(){
  if (timerTask != null) {
    isTimerRunning=false;
    timerTask.cancel();
  }
}

public void stop(){
  if (running) {
    timer.cancel();
    task.cancel();
    running=false;
  }
}

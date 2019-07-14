public void setStop(boolean stop){
  isStop=stop;
  if (isStop) {
    progressColor=pauseColor;
    thread.interrupt();
  }
 else {
    progressColor=loadingColor;
    thread=new Thread(this);
    thread.start();
  }
  invalidate();
}

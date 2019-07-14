private void setFinish(boolean stop){
  isStop=stop;
  if (isStop) {
    progressColor=finishColor;
    thread.interrupt();
  }
 else {
    progressColor=loadingColor;
    thread=new Thread(this);
    thread.start();
  }
  invalidate();
}

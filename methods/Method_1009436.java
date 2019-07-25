public void start(Runnable runnable){
  Thread thread=new Thread(runnable);
  thread.start();
}

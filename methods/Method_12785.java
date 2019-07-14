public void init(){
  RunUtil.getThreadPool().execute(new Runnable(){
    @Override public void run(){
      doInWorkThread();
    }
  }
);
}

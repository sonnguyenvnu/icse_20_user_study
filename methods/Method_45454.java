private void doStart() throws IOException {
  this.service=FileSystems.getDefault().newWatchService();
  this.running=true;
  result=executor.submit(new Runnable(){
    @Override public void run(){
      while (running) {
        loop();
      }
      doStop();
    }
  }
);
}

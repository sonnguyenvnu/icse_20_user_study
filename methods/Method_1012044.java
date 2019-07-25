@Override public void wake(){
  myUpdateQueue.queue(new Update(new Object()){
    @Override public void run(){
      if (myIsDisposed) {
        return;
      }
      bringToFront();
    }
  }
);
}

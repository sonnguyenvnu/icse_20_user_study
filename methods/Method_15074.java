protected void reloadAll(){
  runUiThread(new Runnable(){
    @Override public void run(){
      removeAll(true);
      selectFragment(currentPosition);
    }
  }
);
}

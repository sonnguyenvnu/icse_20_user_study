public void performInContext(final Runnable action){
  if (internal == null) {
    return;
  }
  internal.postRunnable(new Runnable(){
    @Override public void run(){
      if (internal == null || !internal.initialized) {
        return;
      }
      internal.setCurrentContext();
      action.run();
    }
  }
);
}

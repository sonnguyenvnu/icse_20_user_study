@Override public void init(){
  if (INSTANCE != null) {
    throw new IllegalStateException("double initialization");
  }
  myClm.addListener(myClassesListener);
  INSTANCE=this;
}

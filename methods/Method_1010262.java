@Override public void init(){
  if (ourInstance != null) {
    throw new IllegalStateException("Already initialized");
  }
  ourInstance=this;
}

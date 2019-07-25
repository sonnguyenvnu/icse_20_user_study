@Override public void init(){
  if (INSTANCE != null) {
    throw new IllegalStateException("double initialization");
  }
  INSTANCE=this;
  INSTANCE.addResolver(myScopeResolver);
}

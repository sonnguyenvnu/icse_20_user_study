@Override public Callable<?> closer(){
  return new Closer(name,fileChannel,data);
}

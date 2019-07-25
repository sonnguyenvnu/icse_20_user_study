public Callable<?> closer(){
  return new Closer(fileChannel);
}

public final boolean claim(){
  pointer=writeAcquire();
  return pointer != EOF;
}

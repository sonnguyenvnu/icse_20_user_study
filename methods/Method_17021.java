private int getProbe(){
  return UnsafeAccess.UNSAFE.getInt(Thread.currentThread(),probeOffset);
}

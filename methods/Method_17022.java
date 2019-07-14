private void advanceProbe(int probe){
  probe^=probe << 13;
  probe^=probe >>> 17;
  probe^=probe << 5;
  UnsafeAccess.UNSAFE.putInt(Thread.currentThread(),probeOffset,probe);
}

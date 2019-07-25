@Override public final boolean wellformed(final byte[] a,final int astart,final int alength){
  assert (astart + alength <= a.length) : "astart = " + astart + ", alength = " + alength + ", a.length = " + a.length;
  int b;
  for (int i=astart + alength - 1; i >= astart; i--) {
    b=a[i];
    if ((b < 0) || (b >= 128) || (this.ahpla[b] == -1))     return false;
  }
  return true;
}

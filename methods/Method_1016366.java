@Override public final boolean equal(final byte[] a,final byte[] b){
  if ((a == null) && (b == null))   return true;
  if ((a == null) || (b == null))   return false;
  if (a.length != b.length)   return false;
  int astart=0;
  int bstart=0;
  int length=a.length;
  while (length-- != 0) {
    if (a[astart++] != b[bstart++])     return false;
  }
  return true;
}

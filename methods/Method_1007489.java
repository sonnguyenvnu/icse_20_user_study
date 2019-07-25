public boolean fillnb(T[] msg){
  int n=msg.length;
  long currentHead=head.get();
  if ((currentHead + n) > tailCache.value) {
    tailCache.value=tail.get();
  }
  n=(int)Math.min(tailCache.value - currentHead,n);
  if (n == 0) {
    return false;
  }
  int i=0;
  do {
    final int index=(int)(currentHead++) & mask;
    msg[i++]=buffer[index];
    buffer[index]=null;
  }
 while (0 != --n);
  head.lazySet(currentHead);
  return true;
}

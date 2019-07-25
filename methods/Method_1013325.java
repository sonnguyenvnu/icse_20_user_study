public synchronized final boolean contains(long fp){
  int index=(int)(fp & this.mask);
  byte[] bucket=this.table[index];
  byte b1=(byte)((fp >>> 24) & 0xffL);
  byte b2=(byte)((fp >>> 32) & 0xffL);
  byte b3=(byte)((fp >>> 40) & 0xffL);
  byte b4=(byte)((fp >>> 48) & 0xffL);
  byte b5=(byte)((fp >>> 56) & 0xffL);
  int len=bucket == null ? 0 : bucket.length;
  for (int i=0; i < len; i+=5) {
    if (bucket[i] == b1 && bucket[i + 1] == b2 && bucket[i + 2] == b3 && bucket[i + 3] == b4 && bucket[i + 4] == b5)     return true;
  }
  return false;
}

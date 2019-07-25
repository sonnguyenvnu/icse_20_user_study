@Override public long cardinal(final byte[] key,final int off,final int len){
  if (this.zero == null)   return cardinalI(key,off,len);
  final long zeroCardinal=cardinalI(this.zero,0,this.zero.length);
  final long keyCardinal=cardinalI(key,off,len);
  if (keyCardinal > zeroCardinal)   return keyCardinal - zeroCardinal;
  return Long.MAX_VALUE - keyCardinal + zeroCardinal;
}

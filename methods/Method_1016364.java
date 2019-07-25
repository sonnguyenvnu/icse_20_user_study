@Override public final long cardinal(final byte[] key){
  if (this.zero == null)   return cardinalI(key,0,key.length);
  final long zeroCardinal=cardinalI(this.zero,0,this.zero.length);
  final long keyCardinal=cardinalI(key,0,key.length);
  if (keyCardinal > zeroCardinal)   return keyCardinal - zeroCardinal;
  return Long.MAX_VALUE - keyCardinal + zeroCardinal;
}

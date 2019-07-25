public final long cardinal(final String key){
  if (this.zero == null)   return cardinalI(key);
  final long zeroCardinal=cardinalI(this.zero,0,this.zero.length);
  final long keyCardinal=cardinalI(key);
  if (keyCardinal > zeroCardinal)   return keyCardinal - zeroCardinal;
  return Long.MAX_VALUE - keyCardinal + zeroCardinal;
}

public static int unsignedBitLength(final long value){
  return (value == 0) ? 1 : Long.SIZE - Long.numberOfLeadingZeros(value);
}

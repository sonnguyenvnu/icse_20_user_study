private static int unsignedNumBlocks(final long value){
  return numVariableBlocks(unsignedBitLength(value));
}

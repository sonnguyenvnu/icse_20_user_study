private static int numVariableBlocks(final int numBits){
  assert numBits > 0;
  return (numBits - 1) / 7 + 1;
}

private static void checkAlignment(int alignment){
  if (Integer.bitCount(alignment) != 1) {
    throw new IllegalArgumentException("Alignment must be a power-of-two value.");
  }
}

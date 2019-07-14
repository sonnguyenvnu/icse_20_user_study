private static int requireNonNegative(final int value){
  if (value < 0) {
    throw new IllegalArgumentException();
  }
  return value;
}

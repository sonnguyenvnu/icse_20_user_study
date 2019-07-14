public static long apiCheckAllocation(int elements,long bytes,long maxBytes){
  if (DEBUG) {
    if (elements < 0) {
      throw new IllegalArgumentException("Invalid number of elements");
    }
    if ((maxBytes + Long.MIN_VALUE) < (bytes + Long.MIN_VALUE)) {
      throw new IllegalArgumentException("The request allocation is too large");
    }
  }
  return bytes;
}

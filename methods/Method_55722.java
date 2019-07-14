protected static long __checkMalloc(int elements,int elementSize){
  long bytes=(elements & 0xFFFF_FFFFL) * elementSize;
  if (DEBUG) {
    if (elements < 0) {
      throw new IllegalArgumentException("Invalid number of elements");
    }
    if (BITS32 && 0xFFFF_FFFFL < bytes) {
      throw new IllegalArgumentException("The request allocation is too large");
    }
  }
  return bytes;
}

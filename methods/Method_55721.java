private static long getBytes(int elements,int elementSize){
  return (elements & 0xFFFF_FFFFL) * elementSize;
}

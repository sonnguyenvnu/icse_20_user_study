private static void throwIAEGT(int bufferSize,int maximumSize){
  throw new IllegalArgumentException("Number of remaining buffer elements is " + bufferSize + ", must be at most " + maximumSize);
}

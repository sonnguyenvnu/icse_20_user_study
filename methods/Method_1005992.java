private static void duplicate(int length,int distance,ByteArray output){
  int sourceLength=output.length();
  byte[] target=new byte[length];
  int initialPosition=sourceLength - distance;
  int sourceIndex=initialPosition;
  for (int targetIndex=0; targetIndex < length; ++targetIndex, ++sourceIndex) {
    if (sourceLength <= sourceIndex) {
      sourceIndex=initialPosition;
    }
    target[targetIndex]=output.get(sourceIndex);
  }
  output.put(target);
}

public void clear(byte value){
  for (  byte[] aByte : bytes) {
    Arrays.fill(aByte,value);
  }
}

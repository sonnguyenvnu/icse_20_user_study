private static boolean isNotContinuation(int b){
  return (b & 0xc0) != 0x80;
}

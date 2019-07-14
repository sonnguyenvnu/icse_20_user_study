private static boolean isRepeatable(byte cc1){
  return (cc1 & 0xF0) == 0x10;
}

private static boolean isMalformed2(int b1,int b2){
  return (b1 & 0x1e) == 0x0 || (b2 & 0xc0) != 0x80;
}

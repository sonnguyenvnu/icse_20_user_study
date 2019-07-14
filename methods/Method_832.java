private static boolean isMalformed4(int b2,int b3,int b4){
  return (b2 & 0xc0) != 0x80 || (b3 & 0xc0) != 0x80 || (b4 & 0xc0) != 0x80;
}

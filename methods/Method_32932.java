static long encode(int foreColor,int backColor,int effect){
  long result=effect & 0b111111111;
  if ((0xff000000 & foreColor) == 0xff000000) {
    result|=CHARACTER_ATTRIBUTE_TRUECOLOR_FOREGROUND | ((foreColor & 0x00ffffffL) << 40L);
  }
 else {
    result|=(foreColor & 0b111111111L) << 40;
  }
  if ((0xff000000 & backColor) == 0xff000000) {
    result|=CHARACTER_ATTRIBUTE_TRUECOLOR_BACKGROUND | ((backColor & 0x00ffffffL) << 16L);
  }
 else {
    result|=(backColor & 0b111111111L) << 16L;
  }
  return result;
}

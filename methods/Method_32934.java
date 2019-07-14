public static int decodeBackColor(long style){
  if ((style & CHARACTER_ATTRIBUTE_TRUECOLOR_BACKGROUND) == 0) {
    return (int)((style >>> 16) & 0b111111111L);
  }
 else {
    return 0xff000000 | (int)((style >>> 16) & 0x00ffffffL);
  }
}

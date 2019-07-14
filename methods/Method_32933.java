public static int decodeForeColor(long style){
  if ((style & CHARACTER_ATTRIBUTE_TRUECOLOR_FOREGROUND) == 0) {
    return (int)((style >>> 40) & 0b111111111L);
  }
 else {
    return 0xff000000 | (int)((style >>> 40) & 0x00ffffffL);
  }
}

public static String getChar(int type){
  return type < 0 || type >= CHARS.length ? "" : CHARS[type];
}

public static Color decode(String hexColor){
  Integer intval=Integer.decode(hexColor);
  int i=intval.intValue();
  return new Color((i >> 16) & 0xFF,(i >> 8) & 0xFF,i & 0xFF);
}

public static String getHexString(int color,boolean showAlpha){
  int base=showAlpha ? 0xFFFFFFFF : 0xFFFFFF;
  String format=showAlpha ? "#%08X" : "#%06X";
  return String.format(format,(base & color)).toUpperCase();
}

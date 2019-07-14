private String toHexString(int value){
  String str="0000000" + Integer.toHexString(value);
  return str.substring(str.length() - 8,str.length());
}

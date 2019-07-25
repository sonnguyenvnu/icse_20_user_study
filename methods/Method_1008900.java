private static char descape(String hexString){
  int value=Integer.parseInt(hexString,16);
  return (char)value;
}

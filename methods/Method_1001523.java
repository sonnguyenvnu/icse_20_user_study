public String encode(byte data[]){
  if (data == null) {
    return "";
  }
  final StringBuilder result=new StringBuilder((data.length * 4 + 2) / 3);
  for (int i=0; i < data.length; i+=3) {
    append3bytes(result,data[i] & 0xFF,i + 1 < data.length ? data[i + 1] & 0xFF : 0,i + 2 < data.length ? data[i + 2] & 0xFF : 0);
  }
  while (result.length() > 0 && result.charAt(result.length() - 1) == '0') {
    result.setLength(result.length() - 1);
  }
  return result.toString();
}

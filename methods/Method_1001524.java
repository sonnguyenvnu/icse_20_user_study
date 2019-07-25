public String encode(byte data[]){
  if (data == null) {
    return "";
  }
  final StringBuilder result=new StringBuilder(data.length * 2);
  for (  byte b : data) {
    final String val=Integer.toHexString(b & 0xFF);
    if (val.length() == 1) {
      result.append("0");
    }
    result.append(val);
  }
  return result.toString();
}

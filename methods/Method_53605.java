public String[] getStrings(){
  String[] str=new String[values[POS].length];
  for (int i=0; i < str.length; i++) {
    str[i]="" + i;
  }
  return str;
}

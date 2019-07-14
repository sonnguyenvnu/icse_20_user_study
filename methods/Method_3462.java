static private String convert(char[]... keyArray){
  StringBuilder sbKey=new StringBuilder(keyArray.length * 2);
  for (  char[] key : keyArray) {
    sbKey.append(key[0]);
    sbKey.append(key[1]);
  }
  return sbKey.toString();
}

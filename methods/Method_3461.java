private String convert(Collection<char[]> keyArray){
  StringBuilder sbKey=new StringBuilder(keyArray.size() * 2);
  for (  char[] key : keyArray) {
    sbKey.append(key[0]);
    sbKey.append(key[1]);
  }
  return sbKey.toString();
}

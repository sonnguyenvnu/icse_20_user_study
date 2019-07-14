@Override protected CharArray generateKey(String sentence){
  char[] charArray=sentence.toCharArray();
  if (charArray.length == 0)   return null;
  return new CharArray(charArray);
}

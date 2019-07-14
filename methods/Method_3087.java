public static char[] convert(char[] charArray){
  char[] result=new char[charArray.length];
  for (int i=0; i < charArray.length; i++) {
    result[i]=CONVERT[charArray[i]];
  }
  return result;
}

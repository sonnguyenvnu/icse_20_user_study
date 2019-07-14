public static void convert(String charArray,char[] result){
  for (int i=0; i < charArray.length(); i++) {
    result[i]=CONVERT[charArray.charAt(i)];
  }
}

public static int convertTwoCharToInt(char high,char low){
  int result=high << 16;
  result|=low;
  return result;
}

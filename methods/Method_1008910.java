public String format(int num){
  String result="";
  while (num > 0) {
    num--;
    int remainder=num % 26;
    char digit=(char)(remainder + 97);
    result=digit + result;
    num=(num - remainder) / 26;
  }
  return result;
}

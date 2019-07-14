public static String insertCharAt(String word,char c,int i){
  String start=word.substring(0,i);
  String end=word.substring(i);
  return start + c + end;
}

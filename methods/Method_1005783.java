static String minus(String s,String text){
  int index=s.indexOf(text);
  if (index == -1) {
    return s;
  }
 else {
    int end=index + text.length();
    return s.length() > end ? s.substring(0,index) + s.substring(end) : s.substring(0,index);
  }
}

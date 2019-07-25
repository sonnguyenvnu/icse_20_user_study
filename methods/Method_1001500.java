public static int length(CharSequence s){
  int result=0;
  for (int i=0; i < s.length(); i++) {
    result+=of(s.charAt(i));
  }
  return result;
}

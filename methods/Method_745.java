public static boolean checkName(String name){
  for (int i=0; i < name.length(); ++i) {
    char c=name.charAt(i);
    if (c < '\001' || c > '\177' || c == '.') {
      return false;
    }
  }
  return true;
}

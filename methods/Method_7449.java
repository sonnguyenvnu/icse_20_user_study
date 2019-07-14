public static String strip(String str){
  StringBuilder res=new StringBuilder(str);
  String phoneChars="0123456789+*#";
  for (int i=res.length() - 1; i >= 0; i--) {
    if (!phoneChars.contains(res.substring(i,i + 1))) {
      res.deleteCharAt(i);
    }
  }
  return res.toString();
}

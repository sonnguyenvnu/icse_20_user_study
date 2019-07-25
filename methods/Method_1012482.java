static String unescape(String s){
  if (s.equals("\\0"))   return "";
  int i;
  for (i=0; i < s.length(); i++) {
    if (s.charAt(i) == '\\') {
      char c=s.charAt(i + 1);
      if (c == 'n')       s=s.substring(0,i) + "\n" + s.substring(i + 2);
 else       if (c == 'r')       s=s.substring(0,i) + "\r" + s.substring(i + 2);
 else       if (c == 's')       s=s.substring(0,i) + " " + s.substring(i + 2);
 else       if (c == 'p')       s=s.substring(0,i) + "+" + s.substring(i + 2);
 else       if (c == 'q')       s=s.substring(0,i) + "=" + s.substring(i + 2);
 else       if (c == 'h')       s=s.substring(0,i) + "#" + s.substring(i + 2);
 else       if (c == 'a')       s=s.substring(0,i) + "&" + s.substring(i + 2);
 else       s=s.substring(0,i) + s.substring(i + 1);
    }
  }
  return s;
}

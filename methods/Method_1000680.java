public static IntRange make(String s){
  char[] cs=Strings.trim(s).toCharArray();
  int i=0;
  for (; i < cs.length; i++) {
    char c=cs[i];
    if (c == ',' || c == ':')     break;
  }
  if (i == cs.length)   return make(Integer.parseInt(new String(cs)));
  int left=Integer.parseInt(String.valueOf(cs,0,i));
  return make(left,Integer.parseInt(String.valueOf(cs,++i,cs.length - i)));
}

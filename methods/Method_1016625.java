private final static String intf(int i){
  String s=Integer.toString(i);
  while (s.length() < 3)   s='0' + s;
  return s;
}

static boolean contains(String[] a,String name){
  for (  String s : a) {
    if (s.equals(name))     return true;
  }
  return false;
}

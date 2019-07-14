private static boolean contains(Collection<String> collection,String s1){
  for (  String s2 : collection) {
    if (isSame(s1,s2)) {
      return true;
    }
  }
  return false;
}

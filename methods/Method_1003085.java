private static int locate(String search,String s,int start){
  if (start < 0) {
    int i=s.length() + start;
    return s.lastIndexOf(search,i) + 1;
  }
  int i=(start == 0) ? 0 : start - 1;
  return s.indexOf(search,i) + 1;
}

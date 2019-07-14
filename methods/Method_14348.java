static public int countSubstrings(String s,String sub){
  int count=0;
  int from=0;
  while (from < s.length()) {
    int i=s.indexOf(sub,from);
    if (i < 0) {
      break;
    }
 else {
      from=i + sub.length();
      count++;
    }
  }
  return count;
}

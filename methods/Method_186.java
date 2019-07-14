public String countAndSay(int n){
  String s="1";
  for (int i=1; i < n; i++) {
    s=helper(s);
  }
  return s;
}

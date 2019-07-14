private static int getCountOfChar(String value,char c){
  int n=0;
  if (value == null) {
    return 0;
  }
  char[] chars=value.toCharArray();
  for (  char cc : chars) {
    if (cc == c) {
      n++;
    }
  }
  return n;
}

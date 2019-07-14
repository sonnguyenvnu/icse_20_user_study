public static int count(final String source,final String sub,final int start){
  int count=0;
  int j=start;
  int sublen=sub.length();
  if (sublen == 0) {
    return 0;
  }
  while (true) {
    int i=source.indexOf(sub,j);
    if (i == -1) {
      break;
    }
    count++;
    j=i + sublen;
  }
  return count;
}

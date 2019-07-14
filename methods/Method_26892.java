private static int nextStart(@NonNull String s,int end){
  char c;
  while (end < s.length()) {
    c=s.charAt(end);
    if ((((c - 'A') * (c - 'Z') <= 0) || ((c - 'a') * (c - 'z') <= 0)) && c != 'e' && c != 'E') {
      return end;
    }
    end++;
  }
  return end;
}

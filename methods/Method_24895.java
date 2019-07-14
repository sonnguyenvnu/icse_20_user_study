private static int countNewlines(final String s){
  int count=0;
  for (int pos=s.indexOf('\n',0); pos >= 0; pos=s.indexOf('\n',pos + 1))   count++;
  return count;
}

private static final int find(final String s,final String m,final int start){
  final int p=s.indexOf(m,start);
  return (p < 0) ? Integer.MAX_VALUE : p;
}

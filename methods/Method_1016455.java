private static final int find(final String s,final Pattern m,final int start){
  final Matcher mm=m.matcher(s.subSequence(start,s.length()));
  if (!mm.find())   return Integer.MAX_VALUE;
  final int p=mm.start() + start;
  return (p < 0) ? Integer.MAX_VALUE : p;
}

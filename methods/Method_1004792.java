public static List<Object> values(Parser parser,String... paths){
  List<Matcher> matchers=new ArrayList<Matcher>(paths.length);
  int maxNesting=0;
  for (  String path : paths) {
    Matcher matcher=new Matcher(path);
    matchers.add(matcher);
    if (matcher.nesting() > maxNesting) {
      maxNesting=matcher.nesting();
    }
  }
  doFind(parser,matchers,0,maxNesting);
  List<Object> matches=new ArrayList<Object>();
  for (  Matcher matcher : matchers) {
    matches.add(matcher.matched ? matcher.value : NOT_FOUND);
  }
  return matches;
}

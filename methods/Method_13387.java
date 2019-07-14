public List<String> getMatchingPatterns(String lookupPath){
  List<String> matches=new ArrayList<>();
  for (  String pattern : this.patterns) {
    String match=getMatchingPattern(pattern,lookupPath);
    if (match != null) {
      matches.add(match);
    }
  }
  if (matches.size() > 1) {
    matches.sort(this.pathMatcher.getPatternComparator(lookupPath));
  }
  return matches;
}

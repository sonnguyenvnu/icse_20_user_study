public List<String> getMatchingPatterns(HttpRequest request){
  String path=getPath(request);
  List<String> matches=getMatchingPatterns(path);
  return matches;
}

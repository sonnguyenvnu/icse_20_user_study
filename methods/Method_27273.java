private static List<AliasCandidate> getAliasCandidates(String input){
  List<AliasCandidate> candidates=new ArrayList<AliasCandidate>();
  Matcher matcher=ALIAS_CANDIDATE_PATTERN.matcher(input);
  matcher=matcher.useTransparentBounds(true);
  while (matcher.find()) {
    String match=matcher.group();
    if (!match.contains("|")) {
      candidates.add(new AliasCandidate(match,match,null));
    }
 else {
      String[] splitted=match.split("\\|");
      if (splitted.length == 2 || splitted.length > 2) {
        candidates.add(new AliasCandidate(match,splitted[0],splitted[1]));
      }
 else {
        candidates.add(new AliasCandidate(match,match,null));
      }
    }
  }
  return candidates;
}

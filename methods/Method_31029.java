private static void gatherLinks(List<Link> links,CharSequence text,Pattern pattern,String[] schemes,MatchFilter matchFilter){
  Matcher matcher=pattern.matcher(text);
  while (matcher.find()) {
    int start=matcher.start();
    int end=matcher.end();
    if (matchFilter == null || matchFilter.acceptMatch(text,start,end)) {
      links.add(new Link(start,end,makeUrl(matcher.group(0),schemes)));
    }
  }
}

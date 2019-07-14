private void extractLinks(Page page,Selector urlRegionSelector,List<Pattern> urlPatterns){
  List<String> links;
  if (urlRegionSelector == null) {
    links=page.getHtml().links().all();
  }
 else {
    links=page.getHtml().selectList(urlRegionSelector).links().all();
  }
  for (  String link : links) {
    for (    Pattern targetUrlPattern : urlPatterns) {
      Matcher matcher=targetUrlPattern.matcher(link);
      if (matcher.find()) {
        page.addTargetRequest(new Request(matcher.group(0)));
      }
    }
  }
}

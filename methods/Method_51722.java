private Set<String> extractLinkTargets(List<Path> mdFiles){
  final Set<String> htmlPages=new HashSet<>();
  for (  Path mdFile : mdFiles) {
    final String pageContent=fileToString(mdFile);
    final Matcher permalinkMatcher=MD_HEADER_PERMALINK.matcher(pageContent);
    if (!permalinkMatcher.find()) {
      continue;
    }
    final String pageUrl=permalinkMatcher.group(1).replaceAll("^/+","");
    htmlPages.add(pageUrl);
    final Matcher captionMatcher=MD_CAPTION.matcher(pageContent);
    while (captionMatcher.find()) {
      final String anchor=captionMatcher.group(1).toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9_]+","-").replaceAll("^-+|-+$","");
      htmlPages.add(pageUrl + "#" + anchor);
    }
  }
  return htmlPages;
}

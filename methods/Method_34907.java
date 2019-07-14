private static Set<String> extractUrlInCssText(String input){
  Set<String> extractedUrls=new HashSet<>();
  if (input == null || input.isEmpty()) {
    return extractedUrls;
  }
  Matcher matcher=pattern.matcher(input);
  while (matcher.find()) {
    String url=matcher.group(1);
    if (url == null) {
      url=matcher.group(2);
    }
    if (url == null) {
      url=matcher.group(3);
    }
    if (url == null || url.startsWith("data:")) {
      continue;
    }
    extractedUrls.add(url);
  }
  return extractedUrls;
}

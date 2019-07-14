public Set<WebURL> extractUrls(String input){
  if (input == null) {
    return Collections.emptySet();
  }
 else {
    UrlDetector detector=new UrlDetector(input,getOptions());
    List<Url> urls=detector.detect();
    return urls.stream().map(urlMapper).collect(Collectors.toSet());
  }
}

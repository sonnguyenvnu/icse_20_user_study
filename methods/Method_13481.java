public List<URL> toURLs(String urlsJSON){
  List<String> list=toList(urlsJSON);
  return list.stream().map(URL::valueOf).collect(Collectors.toList());
}

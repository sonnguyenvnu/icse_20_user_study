public void unexportURL(URL url){
  String key=url.getServiceKey();
  List<URL> urls=allExportedURLs.get(key);
  if (!isEmpty(urls)) {
    urls.remove(url);
    allExportedURLs.addAll(key,urls);
  }
}

public CompletableFuture<HttpResponse<Path>> download(String url,String fileName,Integer sanity_level,String type){
  HttpRequest request=HttpRequest.newBuilder().uri(URI.create(url)).header(REFERER,PIXIV_REFERER).header(UA,CHROME_UA).GET().build();
  String fullFileName=sanity_level > 5 ? fileName + url.substring(url.length() - 4) : (GIF_TYPE.equals(type) ? fileName + ZIP : fileName + JPG);
  return httpClient.sendAsync(request,HttpResponse.BodyHandlers.ofFile(Paths.get(path,fullFileName))).completeOnTimeout(defaultDownloadHttpResponse,8,TimeUnit.MINUTES);
}

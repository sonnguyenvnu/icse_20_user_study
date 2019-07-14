private CompletableFuture<Integer> getCachedFutureResponse(String url){
  if (urlResponseCache.containsKey(url)) {
    LOG.info("response: HTTP " + urlResponseCache.get(url) + " (CACHED) on " + url);
    return urlResponseCache.get(url);
  }
 else {
    CompletableFuture<Integer> futureResponse=CompletableFuture.supplyAsync(() -> computeHttpResponse(url),executorService);
    urlResponseCache.put(url,futureResponse);
    return futureResponse;
  }
}

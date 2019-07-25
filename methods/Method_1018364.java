public CompletableFuture<ImageUrls> deal(String url,String fileName,Integer sanity_level,String type){
  if (GIF_TYPE.equals(type)) {
    url=GIF_PRE + url.substring(url.indexOf(IMG) + 5,url.length() - 5) + GIF_POS;
  }
  return download(url,fileName,sanity_level,type).whenComplete((resp,throwable) -> {
    Integer respSize=Integer.valueOf(resp.headers().firstValue(CONTENT_LENGTH).get());
    if (respSize > MAXSIZE_LEVEL) {
      System.out.println(fileName + " ??????????---------------");
      int quality=resp.body().endsWith(PNG) ? (respSize > MAXSIZE_LEVEL * 2 ? 70 : 99) : 80;
      try {
        pooledGMService.execute(GM_1 + quality + GM_2 + resp.body().toString() + GM_3 + Paths.get(path,fileName) + JPG);
      }
 catch (      IOException|GMException|GMServiceException e) {
        System.err.println("??????");
      }
      System.out.println(fileName + "????--------------------------------------------");
    }
    if (type.equals("ugoira")) {
      System.out.println(fileName + " ???????,??????ZIP????GIF??");
      try {
        Path path=Paths.get(this.path,fileName);
        zipUtil.unzip(path,resp.body().toString());
        String s=path.toString();
        pooledGMService.execute(GM_4 + s + GM_5 + s + GIF);
        System.out.println(fileName + "??GIF??,????----------------------------");
      }
 catch (      IOException|GMException|GMServiceException e) {
        System.err.println("??????");
      }
    }
  }
).thenApply(HttpResponse::body).thenCompose(body -> {
    try {
      if (GIF_TYPE.equals(type)) {
        Path path=Paths.get(this.path,fileName + GIF);
        return Files.size(path) < MAXSIZE_LEVEL ? uploadToImgBB(path) : uploadToSina(Paths.get(this.path,fileName,"000000.jpg"));
      }
 else {
        return sanity_level < 4 ? uploadToSina(body) : balanceUpload(body);
      }
    }
 catch (    IOException e) {
      return CompletableFuture.completedFuture(IMAGEURLS301);
    }
  }
);
}

public void load(String url){
  if (Objects.nonNull(url))   threadService.runActionLater(() -> {
    webEngine().load(url);
  }
,true);
 else   logger.error("Url is not loaded. Reason: null reference");
}

protected void onSuccess(Request request){
  if (CollectionUtils.isNotEmpty(spiderListeners)) {
    for (    SpiderListener spiderListener : spiderListeners) {
      spiderListener.onSuccess(request);
    }
  }
}

private String makeUrl(){
  if (mResource.hasEffectiveBroadcast()) {
    return mResource.getEffectiveBroadcast().getUrl();
  }
 else {
    return DoubanUtils.makeBroadcastUrl(mResource.getBroadcastId());
  }
}

private String makeUrl(){
  if (mResource.hasSimpleUser()) {
    return mResource.getSimpleUser().getUrl();
  }
 else {
    return DoubanUtils.makeUserUrl(mResource.getUserIdOrUid());
  }
}

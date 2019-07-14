private String makeUrl(){
  if (mResource.hasSimpleItem()) {
    return mResource.getSimpleItem().getUrl();
  }
 else {
    return makeItemUrl(mResource.getItemId());
  }
}

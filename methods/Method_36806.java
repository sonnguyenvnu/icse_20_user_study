public final void doLoadImageUrl(ImageView view,String imgUrl){
  if (serviceManager != null && serviceManager.getService(IInnerImageSetter.class) != null) {
    serviceManager.getService(IInnerImageSetter.class).doLoadImageUrl(view,imgUrl);
  }
 else {
    ImageUtils.doLoadImageUrl(view,imgUrl);
  }
}

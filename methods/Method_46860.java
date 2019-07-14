@Override @Nullable public RequestBuilder<Drawable> getPreloadRequestBuilder(IconDataParcelable iconData){
  RequestBuilder<Drawable> requestBuilder;
  if (iconData.type == IconDataParcelable.IMAGE_FROMFILE) {
    requestBuilder=request.load(iconData.path);
  }
 else   if (iconData.type == IconDataParcelable.IMAGE_FROMCLOUD) {
    requestBuilder=request.load(iconData.path).diskCacheStrategy(DiskCacheStrategy.NONE);
  }
 else {
    requestBuilder=request.load(iconData.image);
  }
  return requestBuilder;
}

private @Nullable Bitmap fetchBitmap(final @Nullable String url,final boolean transformIntoCircle){
  if (url == null) {
    return null;
  }
  try {
    RequestCreator requestCreator=Picasso.with(this.context).load(url).transform(new CropSquareTransformation());
    if (transformIntoCircle) {
      requestCreator=requestCreator.transform(new CircleTransformation());
    }
    return requestCreator.get();
  }
 catch (  IOException e) {
    Timber.e("Failed to load large icon: %s",e);
    return null;
  }
}

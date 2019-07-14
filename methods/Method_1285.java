@Override protected Drawable createDrawable(Bitmap image){
  return new BitmapDrawable(mResources,Preconditions.checkNotNull(image));
}

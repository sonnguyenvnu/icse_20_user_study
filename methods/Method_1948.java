@Override protected void onBind(String uri){
  mPicasso.load(uri).placeholder(Drawables.sPlaceholderDrawable).error(Drawables.sErrorDrawable).fit().into(mImageView);
}

@Override protected void onBind(String uri){
  GlideApp.with(mImageView.getContext()).load(uri).placeholder(Drawables.sPlaceholderDrawable).error(Drawables.sErrorDrawable).transition(withCrossFade()).into(mImageView);
}

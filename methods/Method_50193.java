@Override public void displayImage(Context context,String path,FixImageView imageView,Drawable defaultDrawable,Bitmap.Config config,boolean resize,boolean isGif,int width,int height,int rotate){
  if (isGif) {
    Glide.with(context).load(path).placeholder(defaultDrawable).error(defaultDrawable).override(width,height).crossFade().transform(new RotateTransformation(context,rotate)).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
  }
 else {
    Glide.with(context).load(path).asBitmap().placeholder(defaultDrawable).error(defaultDrawable).override(width,height).transform(new RotateTransformation(context,rotate)).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
  }
}

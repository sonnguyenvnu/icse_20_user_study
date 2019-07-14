@Override public void displayImage(Context context,String path,FixImageView imageView,Drawable defaultDrawable,Bitmap.Config config,boolean resize,boolean isGif,int width,int height,int rotate){
  RequestCreator creator=Picasso.with(context).load(new File(path)).placeholder(defaultDrawable).error(defaultDrawable).rotate(rotate).networkPolicy(NetworkPolicy.NO_STORE).config(config).tag(context);
  if (resize) {
    creator=creator.resize(width,height).centerCrop();
  }
  creator.into(imageView);
}

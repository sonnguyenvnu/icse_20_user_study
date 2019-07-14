@Override public View createThumbnailView(final Context context,final Uri thumbnail,final ImageView.ScaleType scaleType){
  ImageView thumbnailView=new ImageView(context);
  if (scaleType != null) {
    thumbnailView.setScaleType(scaleType);
  }
  Glide.with(context).load(thumbnail).into(thumbnailView);
  return thumbnailView;
}

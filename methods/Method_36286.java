@Override public View createThumbnailView(final Context context,final Uri thumbnail,final ImageView.ScaleType scaleType){
  SimpleDraweeView thumbnailView=new SimpleDraweeView(context);
  DraweeController controller=Fresco.newDraweeControllerBuilder().setUri(thumbnail).build();
  if (scaleType != null) {
    thumbnailView.getHierarchy().setActualImageScaleType(scaleType(scaleType));
  }
  thumbnailView.setController(controller);
  return thumbnailView;
}

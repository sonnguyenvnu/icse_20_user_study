@Override public void displayImage(Context context,String path,FixImageView imageView,Drawable defaultDrawable,Bitmap.Config config,boolean resize,boolean isGif,int width,int height,int rotate){
  init(context,defaultDrawable);
  imageView.setOnImageViewListener(new FixImageView.OnImageViewListener(){
    @Override public void onDetach(){
      draweeHolder.onDetach();
    }
    @Override public void onAttach(){
      draweeHolder.onAttach();
    }
    @Override public boolean verifyDrawable(    Drawable dr){
      return dr == draweeHolder.getHierarchy().getTopLevelDrawable();
    }
    @Override public void onDraw(    Canvas canvas){
      Drawable drawable=draweeHolder.getHierarchy().getTopLevelDrawable();
      if (drawable == null) {
        imageView.setImageDrawable(defaultDrawable);
      }
 else {
        imageView.setImageDrawable(drawable);
      }
    }
    @Override public boolean onTouchEvent(    MotionEvent event){
      return draweeHolder.onTouchEvent(event);
    }
  }
);
  Uri uri=new Uri.Builder().scheme(UriUtil.LOCAL_FILE_SCHEME).path(path).build();
  ImageRequestBuilder builder=ImageRequestBuilder.newBuilderWithSource(uri).setAutoRotateEnabled(true);
  if (resize) {
    builder.setResizeOptions(new ResizeOptions(width,height));
  }
  ImageRequest request=builder.build();
  DraweeController controller=Fresco.newDraweeControllerBuilder().setOldController(draweeHolder.getController()).setImageRequest(request).build();
  draweeHolder.setController(controller);
}

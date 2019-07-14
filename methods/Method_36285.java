@Override protected View createAnimatedImageView(final Context context,final int imageType,final File imageFile,int initScaleType){
  SimpleDraweeView view=new SimpleDraweeView(context);
  DraweeController controller=Fresco.newDraweeControllerBuilder().setUri(Uri.parse("file://" + imageFile.getAbsolutePath())).setAutoPlayAnimations(true).build();
  view.setController(controller);
  view.getHierarchy().setActualImageScaleType(scaleType(initScaleType));
  return view;
}

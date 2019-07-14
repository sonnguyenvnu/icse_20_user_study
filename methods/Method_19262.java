@OnLoadStyle static void onLoadStyle(ComponentContext c,Output<Drawable> indeterminateDrawable){
  indeterminateDrawable.set(getStyledIndeterminateDrawable(c,0));
}

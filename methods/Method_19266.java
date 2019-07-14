@OnUnmount static void onUnmount(ComponentContext c,ProgressBar progressBar,@Prop(optional=true,resType=ResType.COLOR) int color,@FromPrepare Drawable resolvedIndeterminateDrawable){
  if (color != Color.TRANSPARENT && progressBar.getIndeterminateDrawable() != null) {
    progressBar.getIndeterminateDrawable().mutate().clearColorFilter();
  }
  progressBar.setIndeterminateDrawable(null);
}

@OnMount static void onMount(ComponentContext c,ProgressBar progressBar,@Prop(optional=true,resType=ResType.COLOR) int color,@FromPrepare Drawable resolvedIndeterminateDrawable){
  if (resolvedIndeterminateDrawable != null) {
    progressBar.setIndeterminateDrawable(resolvedIndeterminateDrawable);
  }
  if (color != Color.TRANSPARENT && progressBar.getIndeterminateDrawable() != null) {
    progressBar.getIndeterminateDrawable().mutate().setColorFilter(color,PorterDuff.Mode.MULTIPLY);
  }
}

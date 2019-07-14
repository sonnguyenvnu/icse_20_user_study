@OnPrepare static void onPrepare(ComponentContext c,@Prop(optional=true,resType=ResType.DRAWABLE) Drawable indeterminateDrawable,Output<Drawable> resolvedIndeterminateDrawable){
  if (indeterminateDrawable != null) {
    resolvedIndeterminateDrawable.set(indeterminateDrawable);
  }
 else {
    resolvedIndeterminateDrawable.set(getStyledIndeterminateDrawable(c,android.R.attr.progressBarStyle));
  }
}

@OnCreateLayout static Component onCreateLayout(ComponentContext c,@Prop(resType=COLOR) int color,@Prop(optional=true,isCommonProp=true,overrideCommonPropBehavior=true) float alpha){
  if (alpha >= 0f) {
    alpha=Math.min(1f,alpha);
    color=ColorUtils.setAlphaComponent(color,(int)(alpha * 255f));
  }
  return Image.create(c).scaleType(FIT_XY).drawable(new ColorDrawable(color)).build();
}

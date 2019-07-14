@OnCreateLayout static Component onCreateLayout(ComponentContext c,@Prop(resType=ResType.DIMEN_SIZE) int radius,@Prop(resType=ResType.COLOR) int color){
  final int dim=2 * radius;
  return Row.create(c).heightPx(dim).widthPx(dim).background(buildRoundedRect(radius,color)).build();
}

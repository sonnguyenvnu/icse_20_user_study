@OnCreateLayout static Component onCreateLayout(ComponentContext c,@State boolean state){
  final int color=state ? Color.RED : Color.LTGRAY;
  final String key=state ? "red" : "gray";
  return Column.create(c).child(buildRow(c,color,key + 0)).child(buildRow(c,color,key + 1)).paddingDip(YogaEdge.ALL,8).clickHandler(RTAnimationComponent.onClick(c)).build();
}

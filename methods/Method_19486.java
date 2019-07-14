@OnCreateLayout static Component onCreateLayout(ComponentContext c,@State int state){
  final boolean expanded1=state == 1 || state == 2;
  final boolean expanded2=state == 2 || state == 3;
  return Column.create(c).paddingDip(YogaEdge.ALL,8).child(Row.create(c).marginDip(YogaEdge.TOP,8).child(buildComment1(c,expanded1))).child(Row.create(c).marginDip(YogaEdge.TOP,16).child(buildComment2(c,expanded2))).clickHandler(AnimatedBadge.onClick(c)).build();
}

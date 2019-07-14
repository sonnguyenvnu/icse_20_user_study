@OnCreateLayout static Component onCreateLayout(ComponentContext c,@Prop int size,@Prop int selectedIndex,@Prop int firstVisibleIndex){
  Row.Builder row=Row.create(c).alignItems(YogaAlign.CENTER);
  final int dotCount=Math.min(size,MAX_DOT_COUNT);
  for (int position=0; position < dotCount; ++position) {
    final int index=firstVisibleIndex + position;
    row.child(Circle.create(c).radiusDip(2 * getIndicatorSize(size,firstVisibleIndex,position,selectedIndex)).color(index == selectedIndex ? COLOR_SELECTED : COLOR_NORMAL).transitionKey("dot" + index).transitionKeyType(Transition.TransitionKeyType.GLOBAL).marginDip(YogaEdge.ALL,2 * 1));
  }
  return row.build();
}

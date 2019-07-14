private static Component buildCell(ComponentContext c,int color,String key){
  return Column.create(c).flexGrow(1f).aspectRatio(0.75f).marginDip(YogaEdge.ALL,8).background(buildRoundedRect(c,color,8)).transitionKey(key).transitionKeyType(Transition.TransitionKeyType.GLOBAL).build();
}

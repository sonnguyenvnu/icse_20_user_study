private static Component affectedParent(ComponentContext c,boolean flag3){
  return Row.create(c).justifyContent(YogaJustify.CENTER).child(Row.create(c).transitionKey(TRANSITION_KEY_CONTAINER_3).transitionKeyType(Transition.TransitionKeyType.GLOBAL).heightDip(60 + 2 * 8).paddingDip(YogaEdge.ALL,8).backgroundColor(Color.LTGRAY).child(Column.create(c).transitionKey(TRANSITION_KEY_CHILD_3_1).transitionKeyType(Transition.TransitionKeyType.GLOBAL).widthDip(60 * (flag3 ? 1 : 2)).backgroundColor(Color.YELLOW)).clickHandler(BoundsAnimationComponent.onThirdComponentClick(c))).build();
}

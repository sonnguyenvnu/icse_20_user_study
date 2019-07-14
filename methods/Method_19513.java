private static Component affectedSiblings(ComponentContext c,boolean flag2){
  return Row.create(c).transitionKey(TRANSITION_KEY_CONTAINER_2).transitionKeyType(Transition.TransitionKeyType.GLOBAL).heightDip(60 + 2 * 8).widthDip(3 * 60 + 3 * 8).paddingDip(YogaEdge.ALL,8).backgroundColor(Color.LTGRAY).child(Column.create(c).transitionKey(TRANSITION_KEY_CHILD_2_1).transitionKeyType(Transition.TransitionKeyType.GLOBAL).flex(1).backgroundColor(Color.RED)).child(Column.create(c).transitionKey(TRANSITION_KEY_CHILD_2_2).transitionKeyType(Transition.TransitionKeyType.GLOBAL).flex(flag2 ? 1 : 2).backgroundColor(Color.YELLOW).marginDip(YogaEdge.LEFT,8)).clickHandler(BoundsAnimationComponent.onSecondComponentClick(c)).build();
}
@Nullable static Component.Builder maybeCreateBottomDetailComponent(ComponentContext c,boolean expanded,boolean seen){
  if (!expanded) {
    return null;
  }
  return Text.create(c).textSizeDip(14).textColor(Color.GRAY).alignSelf(YogaAlign.FLEX_END).paddingDip(YogaEdge.RIGHT,10).transitionKey(TRANSITION_BOTTOM_DETAIL).transitionKeyType(Transition.TransitionKeyType.GLOBAL).text(seen ? "Seen" : "Sent");
}

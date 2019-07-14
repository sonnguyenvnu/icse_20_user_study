@Nullable static Component.Builder maybeCreateTopDetailComponent(ComponentContext c,boolean expanded,String timestamp){
  if (!expanded) {
    return null;
  }
  return Text.create(c).textSizeDip(14).textColor(Color.GRAY).alignSelf(YogaAlign.CENTER).transitionKey(TRANSITION_TOP_DETAIL).transitionKeyType(Transition.TransitionKeyType.GLOBAL).text(timestamp);
}

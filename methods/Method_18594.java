private static ComponentTargetType getComponentTargetTypeForTransitionKeyType(TransitionKeyType transitionKeyType,boolean isSingleKey){
  if (transitionKeyType == TransitionKeyType.GLOBAL) {
    return isSingleKey ? ComponentTargetType.GLOBAL_KEY : ComponentTargetType.GLOBAL_KEY_SET;
  }
 else   if (transitionKeyType == TransitionKeyType.LOCAL) {
    return isSingleKey ? ComponentTargetType.LOCAL_KEY : ComponentTargetType.LOCAL_KEY_SET;
  }
 else {
    throw new RuntimeException("Unhandled TransitionKeyType " + transitionKeyType);
  }
}

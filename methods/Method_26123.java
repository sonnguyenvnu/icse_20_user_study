private static Matcher<MethodInvocationTree> buildMatcher(){
  return anyOf(allOf(anyOf(instanceMethod().onDescendantOf("java.util.Collection").named("addAll"),instanceMethod().onDescendantOf("java.util.Collection").named("removeAll"),instanceMethod().onDescendantOf("java.util.Collection").named("containsAll"),instanceMethod().onDescendantOf("java.util.Collection").named("retainAll")),receiverSameAsArgument(0)),allOf(instanceMethod().onDescendantOf("java.util.Collection").named("addAll"),receiverSameAsArgument(1)));
}

private static final Matcher<MethodInvocationTree> createFieldMatcher(String className){
  String builderName=className + ".Builder";
  return anyOf(instanceMethod().onExactClass(className).withNameMatching(PRIVATE_DO_NOT_ACCESS_OR_ELSE),instanceMethod().onExactClass(builderName).withNameMatching(PRIVATE_DO_NOT_ACCESS_OR_ELSE));
}

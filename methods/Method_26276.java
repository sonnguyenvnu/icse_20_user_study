private static Matcher<ExpressionTree> classHasIsEmptyFunction(){
  ImmutableList.Builder<String> classNames=ImmutableList.builder();
  for (  Cell<String,MethodName,Boolean> methodInformation : Iterables.concat(CLASSES.cellSet(),STATIC_CLASSES.cellSet())) {
    if (methodInformation.getValue()) {
      classNames.add(methodInformation.getRowKey());
    }
  }
  return anyOf(classNames.build().stream().map(Matchers::isSubtypeOf).collect(toImmutableList()));
}

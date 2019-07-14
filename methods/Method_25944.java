private static Matcher<ExpressionTree> setFactory(String factoryName){
  return MethodMatchers.staticMethod().onClass(Sets.class.getCanonicalName()).named(factoryName).withParameters();
}

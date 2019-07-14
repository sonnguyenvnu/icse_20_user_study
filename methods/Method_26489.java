private static Matcher<ExpressionTree> factoryMatcher(Class<?> clazz,String name){
  return staticMethod().onClass(clazz.getCanonicalName()).named(name).withParameters();
}

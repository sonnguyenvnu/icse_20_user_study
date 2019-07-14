@SuppressWarnings("rawtypes") private static Matcher<ExpressionTree> noArgSetConstructor(Class<? extends Set> setClass){
  return MethodMatchers.constructor().forClass(setClass.getCanonicalName()).withParameters();
}

private static Matcher<MethodTree> returning(String type){
  return methodReturns(typeFromString(type));
}

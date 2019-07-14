public static Matcher<MethodTree> methodReturns(Matcher<? super Tree> returnTypeMatcher){
  return (methodTree,state) -> {
    Tree returnTree=methodTree.getReturnType();
    return returnTree != null && returnTypeMatcher.matches(returnTree,state);
  }
;
}

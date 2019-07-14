@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (!inInstanceMethodOfSubjectImplementation(state)) {
    return NO_MATCH;
  }
  if (STANDARD_ASSERT_THAT.matches(tree,state)) {
    String checkDescription=makeCheckDescription(getOnlyElement(tree.getArguments()),state);
    if (checkDescription == null) {
      return NO_MATCH;
    }
    return replace(tree.getMethodSelect(),"check(%s).that",checkDescription);
  }
 else   if (ANY_ASSERT_THAT.matches(tree,state)) {
    FactoryMethodName factory=tryFindFactory(tree,state);
    if (factory == null) {
      return NO_MATCH;
    }
    if (tree.getArguments().size() != 1) {
      return NO_MATCH;
    }
    String checkDescription=makeCheckDescription(getOnlyElement(tree.getArguments()),state);
    if (checkDescription == null) {
      return NO_MATCH;
    }
    return describeMatch(tree,SuggestedFix.builder().addStaticImport(factory.clazz() + "." + factory.method()).replace(tree.getMethodSelect(),format("check(%s).about(%s()).that",checkDescription,factory.method())).build());
  }
 else   if (ASSERT_ABOUT.matches(tree,state)) {
    String checkDescription=findThatCallAndMakeCheckDescription(state);
    if (checkDescription == null) {
      return NO_MATCH;
    }
    return replace(tree.getMethodSelect(),"check(%s).about",checkDescription);
  }
 else   if (ASSERT_WITH_MESSAGE.matches(tree,state)) {
    String checkDescription=findThatCallAndMakeCheckDescription(state);
    if (checkDescription == null) {
      return NO_MATCH;
    }
    return replace(tree.getMethodSelect(),"check(%s).withMessage",checkDescription);
  }
 else   if (ASSERT.matches(tree,state)) {
    String checkDescription=findThatCallAndMakeCheckDescription(state);
    if (checkDescription == null) {
      return NO_MATCH;
    }
    return replace(tree,"check(%s)",checkDescription);
  }
 else {
    return NO_MATCH;
  }
}

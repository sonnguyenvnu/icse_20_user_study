@Override public Description matchClass(ClassTree tree,VisitorState state){
  if (ASTHelpers.hasAnnotation(tree,"com.google.auto.value.AutoValue",state)) {
    for (    Tree memberTree : tree.getMembers()) {
      if (memberTree instanceof MethodTree && !isSuppressed(memberTree)) {
        MethodTree methodTree=(MethodTree)memberTree;
        if (ABSTRACT_MATCHER.matches(methodTree,state)) {
          for (          Map.Entry<String,Matcher<MethodTree>> entry : REPLACEMENT_TO_MATCHERS.entries()) {
            if (entry.getValue().matches(methodTree,state)) {
              state.reportMatch(buildDescription(methodTree).setMessage(String.format(MESSAGE,entry.getKey())).build());
            }
          }
        }
      }
    }
  }
  return NO_MATCH;
}

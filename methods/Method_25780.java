/** 
 * Starting from a  {@code VisitorState} pointing at part of a fluent assertion statement (like{@code check()} or {@code assertWithMessage()}, walks up the tree and returns the subsequent call to  {@code that(...)}. <p>Often, the call is made directly on the result of the given tree -- like when the input is {@code check()}, which is part of the expression  {@code check().that(...)}. But sometimes there is an intervening call to  {@code withMessage},  {@code about}, or both.
 */
static MethodInvocationTree findThatCall(VisitorState state){
  TreePath path=state.getPath();
  while (true) {
    path=path.getParentPath().getParentPath();
    Tree leaf=path.getLeaf();
    if (leaf.getKind() != METHOD_INVOCATION) {
      return null;
    }
    MethodInvocationTree maybeThatCall=(MethodInvocationTree)leaf;
    if (WITH_MESSAGE_OR_ABOUT.matches(maybeThatCall,state)) {
      continue;
    }
 else     if (SUBJECT_BUILDER_THAT.matches(maybeThatCall,state)) {
      return maybeThatCall;
    }
 else {
      return null;
    }
  }
}

private static Matcher<? super MethodInvocationTree> receiverSameAsParentsArgument(){
  return new Matcher<MethodInvocationTree>(){
    @Override public boolean matches(    MethodInvocationTree t,    VisitorState state){
      ExpressionTree rec=ASTHelpers.getReceiver(t);
      if (rec == null) {
        return false;
      }
      if (!ASSERT_THAT.matches(rec,state)) {
        return false;
      }
      if (!ASTHelpers.sameVariable(getOnlyElement(((MethodInvocationTree)rec).getArguments()),getOnlyElement(t.getArguments()))) {
        return false;
      }
      return true;
    }
  }
;
}

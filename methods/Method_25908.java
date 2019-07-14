private static boolean isObjectReturningMethodReferenceExpression(MemberReferenceTree tree,VisitorState state){
  return functionalInterfaceReturnsObject(((JCMemberReference)tree).type,state);
}

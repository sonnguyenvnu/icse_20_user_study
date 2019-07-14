private static String simpleNameOfIdentifierOrMemberAccess(ExpressionTree tree){
  String name=null;
  if (tree.getKind() == IDENTIFIER) {
    name=((JCIdent)tree).name.toString();
  }
 else   if (tree.getKind() == MEMBER_SELECT) {
    name=((JCFieldAccess)tree).name.toString();
  }
  return name;
}

private static boolean isSuper(ExpressionTree tree){
switch (tree.getKind()) {
case IDENTIFIER:
    return ((IdentifierTree)tree).getName().contentEquals("super");
case MEMBER_SELECT:
  return ((MemberSelectTree)tree).getIdentifier().contentEquals("super");
default :
return false;
}
}

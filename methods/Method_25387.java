/** 
 * Matches an AST node if it is a literal other than null. 
 */
public static Matcher<ExpressionTree> nonNullLiteral(){
  return (tree,state) -> {
switch (tree.getKind()) {
case MEMBER_SELECT:
      return ((MemberSelectTree)tree).getIdentifier().contentEquals("class");
case INT_LITERAL:
case LONG_LITERAL:
case FLOAT_LITERAL:
case DOUBLE_LITERAL:
case BOOLEAN_LITERAL:
case CHAR_LITERAL:
case STRING_LITERAL:
    return true;
default :
  return false;
}
}
;
}

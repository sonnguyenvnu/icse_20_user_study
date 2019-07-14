private static List<String> getArgumentTypes(ASTArguments args){
  List<String> argumentTypes=new ArrayList<>();
  ASTArgumentList argumentList=args.getFirstChildOfType(ASTArgumentList.class);
  if (argumentList != null) {
    for (int a=0; a < argumentList.jjtGetNumChildren(); a++) {
      Node expression=argumentList.jjtGetChild(a);
      ASTPrimaryPrefix arg=expression.getFirstDescendantOfType(ASTPrimaryPrefix.class);
      String type="<unknown>";
      if (arg != null && arg.jjtGetNumChildren() > 0) {
        if (arg.jjtGetChild(0) instanceof ASTLiteral) {
          ASTLiteral lit=(ASTLiteral)arg.jjtGetChild(0);
          if (lit.isCharLiteral()) {
            type="char";
          }
 else           if (lit.isFloatLiteral()) {
            type="float";
          }
 else           if (lit.isIntLiteral()) {
            type="int";
          }
 else           if (lit.isStringLiteral()) {
            type="String";
          }
 else           if (lit.jjtGetNumChildren() > 0 && lit.jjtGetChild(0) instanceof ASTBooleanLiteral) {
            type="boolean";
          }
 else           if (lit.isDoubleLiteral()) {
            type="double";
          }
 else           if (lit.isLongLiteral()) {
            type="long";
          }
        }
 else         if (arg.jjtGetChild(0) instanceof ASTName) {
          type="ref";
        }
      }
      argumentTypes.add(type);
    }
  }
  return argumentTypes;
}

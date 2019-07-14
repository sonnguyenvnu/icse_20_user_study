private int getConstructorAppendsLength(final Node node){
  final Node parent=node.getFirstParentOfType(ASTVariableDeclarator.class);
  int size=0;
  if (parent != null) {
    final Node initializer=parent.getFirstChildOfType(ASTVariableInitializer.class);
    if (initializer != null) {
      final Node primExp=initializer.getFirstDescendantOfType(ASTPrimaryExpression.class);
      if (primExp != null) {
        for (int i=0; i < primExp.jjtGetNumChildren(); i++) {
          final Node sn=primExp.jjtGetChild(i);
          if (!(sn instanceof ASTPrimarySuffix) || sn.getImage() != null) {
            continue;
          }
          size+=processNode(sn);
        }
      }
    }
  }
  return size;
}

private int getConstructorLength(Node node,int constructorLength){
  int iConstructorLength=constructorLength;
  Node block=node.getFirstParentOfType(ASTBlockStatement.class);
  if (block == null) {
    block=node.getFirstParentOfType(ASTFieldDeclaration.class);
  }
  if (block == null) {
    block=node.getFirstParentOfType(ASTFormalParameter.class);
    if (block != null) {
      iConstructorLength=-1;
    }
 else {
      return DEFAULT_BUFFER_SIZE;
    }
  }
  ASTAdditiveExpression exp=block.getFirstDescendantOfType(ASTAdditiveExpression.class);
  if (exp != null) {
    return DEFAULT_BUFFER_SIZE;
  }
  ASTMultiplicativeExpression mult=block.getFirstDescendantOfType(ASTMultiplicativeExpression.class);
  if (mult != null) {
    return DEFAULT_BUFFER_SIZE;
  }
  List<ASTLiteral> literals;
  ASTAllocationExpression constructorCall=block.getFirstDescendantOfType(ASTAllocationExpression.class);
  if (constructorCall != null) {
    literals=constructorCall.findDescendantsOfType(ASTLiteral.class);
  }
 else {
    literals=block.findDescendantsOfType(ASTLiteral.class);
  }
  if (literals.isEmpty()) {
    List<ASTName> name=block.findDescendantsOfType(ASTName.class);
    if (!name.isEmpty()) {
      iConstructorLength=-1;
    }
  }
 else   if (literals.size() == 1) {
    ASTLiteral literal=literals.get(0);
    String str=literal.getImage();
    if (str == null) {
      iConstructorLength=0;
    }
 else     if (isStringOrCharLiteral(literal)) {
      iConstructorLength=14 + str.length();
    }
 else     if (literal.isIntLiteral()) {
      iConstructorLength=literal.getValueAsInt();
    }
  }
 else {
    iConstructorLength=-1;
  }
  if (iConstructorLength == 0) {
    if (constructorLength == -1) {
      iConstructorLength=DEFAULT_BUFFER_SIZE;
    }
 else {
      iConstructorLength=constructorLength;
    }
  }
  return iConstructorLength;
}

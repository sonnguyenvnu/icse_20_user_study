@Override void visitVariableDeclaration(VariableDeclarationNode node,SubNodeValues inputs,Updates updates){
  if (isCatchVariable(node)) {
    updates.set(node,NONNULL);
  }
}

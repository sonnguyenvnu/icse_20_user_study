private String getNameImage(ASTPrimaryExpression node){
  ASTPrimaryPrefix prefix=node.getFirstDescendantOfType(ASTPrimaryPrefix.class);
  ASTName name=prefix.getFirstDescendantOfType(ASTName.class);
  String image=null;
  if (name != null) {
    image=name.getImage();
  }
  return image;
}

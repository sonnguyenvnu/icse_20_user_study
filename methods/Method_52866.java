private void dump(Node node,String prefix){
  writer.print(prefix);
  writer.print(node.getXPathNodeName());
  String image=node.getImage();
  List<String> extras=new ArrayList<>();
  if (node instanceof ASTAttribute) {
    extras.add("name=[" + ((ASTAttribute)node).getName() + "]");
  }
 else   if (node instanceof ASTDeclaration) {
    extras.add("name=[" + ((ASTDeclaration)node).getName() + "]");
  }
 else   if (node instanceof ASTDoctypeDeclaration) {
    extras.add("name=[" + ((ASTDoctypeDeclaration)node).getName() + "]");
  }
 else   if (node instanceof ASTDoctypeExternalId) {
    extras.add("uri=[" + ((ASTDoctypeExternalId)node).getUri() + "]");
    if (((ASTDoctypeExternalId)node).getPublicId().length() > 0) {
      extras.add("publicId=[" + ((ASTDoctypeExternalId)node).getPublicId() + "]");
    }
  }
 else   if (node instanceof ASTElement) {
    extras.add("name=[" + ((ASTElement)node).getName() + "]");
    if (((ASTElement)node).isEmpty()) {
      extras.add("empty");
    }
  }
  if (image != null || !extras.isEmpty()) {
    writer.print(':');
    if (image != null) {
      writer.print(image);
    }
    for (    String extra : extras) {
      writer.print('(');
      writer.print(extra);
      writer.print(')');
    }
  }
  writer.println();
}

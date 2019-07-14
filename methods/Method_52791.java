private void dump(PLSQLNode node,String prefix){
  writer.print(prefix);
  writer.print(node.getXPathNodeName());
  String image=node.getImage();
  if (node instanceof ASTBooleanLiteral) {
    image=node.getImage();
  }
 else   if (node instanceof ASTPrimaryPrefix) {
    String result=null;
    if (image != null) {
      result+="." + image;
    }
    image=result;
  }
 else   if (node instanceof ASTPrimarySuffix) {
    ASTPrimarySuffix primarySuffix=(ASTPrimarySuffix)node;
    if (primarySuffix.isArrayDereference()) {
      if (image == null) {
        image="[";
      }
 else {
        image="[" + image;
      }
    }
  }
  List<String> extras=new ArrayList<>();
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

private int getBadPrefixOrNull(ASTPrimaryExpression exp,int childrenCount){
  for (int i=0; i < childrenCount - 3; i++) {
    Node child=exp.jjtGetChild(i);
    String image;
    if (child instanceof ASTPrimaryPrefix) {
      if (child.jjtGetNumChildren() != 1 || !(child.jjtGetChild(0) instanceof ASTName)) {
        continue;
      }
      ASTName name=(ASTName)child.jjtGetChild(0);
      image=name.getImage();
    }
 else     if (child instanceof ASTPrimarySuffix) {
      image=((ASTPrimarySuffix)child).getImage();
    }
 else {
      continue;
    }
    if (image == null || !(image.endsWith("toUpperCase") || image.endsWith("toLowerCase"))) {
      continue;
    }
 else {
      return i;
    }
  }
  return -1;
}

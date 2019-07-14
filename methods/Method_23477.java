static protected void copyGroup(PApplet parent,PShape src,PShape dest){
  copyMatrix(src,dest);
  copyStyles(src,dest);
  copyImage(src,dest);
  for (int i=0; i < src.childCount; i++) {
    PShape c=PShape.createShape(parent,src.children[i]);
    dest.addChild(c);
  }
}

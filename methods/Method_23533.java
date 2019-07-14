protected void parseChildren(XML graphics){
  XML[] elements=graphics.getChildren();
  children=new PShape[elements.length];
  childCount=0;
  for (  XML elem : elements) {
    PShape kid=parseChild(elem);
    if (kid != null)     addChild(kid);
  }
  children=(PShape[])PApplet.subset(children,0,childCount);
}

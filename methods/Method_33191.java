private double getHPosForNode(Node node){
  double hx=-margin;
switch (pos.getHpos()) {
case CENTER:
    hx=(node.getBoundsInParent().getWidth() / 2);
  break;
case RIGHT:
hx=node.getBoundsInParent().getWidth() + margin;
break;
}
return hx;
}

private double getVPosForNode(Node node){
  double vy=-margin;
switch (pos.getVpos()) {
case CENTER:
    vy=(node.getBoundsInParent().getHeight() / 2);
  break;
case BOTTOM:
vy=node.getBoundsInParent().getHeight() + margin;
break;
}
return vy;
}

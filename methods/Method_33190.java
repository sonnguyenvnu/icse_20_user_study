private double getUpdatedAnchorX(){
switch (pos.getHpos()) {
case CENTER:
    return getAnchorX() - getWidth() / 2;
case LEFT:
  return getAnchorX() - getWidth();
default :
return getAnchorX();
}
}

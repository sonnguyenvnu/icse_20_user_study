@Override public boolean shouldDrawBorders(){
  return hasBorderColor() && (mYogaNode.getLayoutBorder(LEFT) != 0 || mYogaNode.getLayoutBorder(TOP) != 0 || mYogaNode.getLayoutBorder(RIGHT) != 0 || mYogaNode.getLayoutBorder(BOTTOM) != 0);
}

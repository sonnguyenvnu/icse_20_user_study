@Override protected void layoutChildren(final double x,final double y,final double w,final double h){
  final CheckBox checkBox=getSkinnable();
  boxWidth=snapSize(container.prefWidth(-1));
  boxHeight=snapSize(container.prefHeight(-1));
  final double computeWidth=Math.min(checkBox.prefWidth(-1),checkBox.minWidth(-1)) + labelOffset + 2 * padding;
  final double labelWidth=Math.min(computeWidth - boxWidth,w - snapSize(boxWidth)) + labelOffset + 2 * padding;
  final double labelHeight=Math.min(checkBox.prefHeight(labelWidth),h);
  maxHeight=Math.max(boxHeight,labelHeight);
  final double xOffset=computeXOffset(w,labelWidth + boxWidth,checkBox.getAlignment().getHpos()) + x;
  final double yOffset=computeYOffset(h,maxHeight,checkBox.getAlignment().getVpos()) + x;
  if (invalid) {
    rightLine.setStartX((boxWidth + padding - labelOffset) / 2 - boxWidth / 5.5);
    rightLine.setStartY(maxHeight - padding - lineThick);
    rightLine.setEndX((boxWidth + padding - labelOffset) / 2 - boxWidth / 5.5);
    rightLine.setEndY(maxHeight - padding - lineThick);
    leftLine.setStartX((boxWidth + padding - labelOffset) / 2 - boxWidth / 5.5);
    leftLine.setStartY(maxHeight - padding - lineThick);
    leftLine.setEndX((boxWidth + padding - labelOffset) / 2 - boxWidth / 5.5);
    leftLine.setEndY(maxHeight - padding - lineThick);
    transition=new CheckBoxTransition();
    if (getSkinnable().isSelected()) {
      transition.play();
    }
    invalid=false;
  }
  layoutLabelInArea(xOffset + boxWidth,yOffset,labelWidth,maxHeight,checkBox.getAlignment());
  container.resize(boxWidth,boxHeight);
  positionInArea(container,xOffset,yOffset,boxWidth,maxHeight,0,checkBox.getAlignment().getHpos(),checkBox.getAlignment().getVpos());
}

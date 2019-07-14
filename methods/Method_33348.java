@Override protected void layoutChildren(final double x,final double y,final double w,final double h){
  super.layoutChildren(x,y,w,h);
  final double height=getSkinnable().getHeight();
  linesWrapper.layoutLines(x,y,w,h,height,promptText == null ? 0 : promptText.getLayoutBounds().getHeight() + 3);
  errorContainer.layoutPane(x,height + linesWrapper.focusedLine.getHeight(),w,h);
  linesWrapper.updateLabelFloatLayout();
  if (invalid) {
    invalid=false;
    Region viewPort=(Region)scrollPane.getChildrenUnmodifiable().get(0);
    viewPort.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,CornerRadii.EMPTY,Insets.EMPTY)));
    viewPort.applyCss();
    errorContainer.invalid(w);
    linesWrapper.invalid();
  }
}

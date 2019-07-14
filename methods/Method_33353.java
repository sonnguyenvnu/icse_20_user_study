private void updateTextPos(){
  double textWidth=textNode.getLayoutBounds().getWidth();
  final double promptWidth=promptText == null ? 0 : promptText.getLayoutBounds().getWidth();
switch (getHAlignment()) {
case CENTER:
    linesWrapper.promptTextScale.setPivotX(promptWidth / 2);
  double midPoint=textRight.get() / 2;
double newX=midPoint - textWidth / 2;
if (newX + textWidth <= textRight.get()) {
textTranslateX.set(newX);
}
break;
case LEFT:
linesWrapper.promptTextScale.setPivotX(0);
break;
case RIGHT:
linesWrapper.promptTextScale.setPivotX(promptWidth);
break;
}
}

/** 
 * generate the clipping mask
 * @return the mask node
 */
protected Node getMask(){
  double borderWidth=ripplerPane.getBorder() != null ? ripplerPane.getBorder().getInsets().getTop() : 0;
  Bounds bounds=control.getBoundsInParent();
  double width=control.getLayoutBounds().getWidth();
  double height=control.getLayoutBounds().getHeight();
  double diffMinX=Math.abs(control.getBoundsInLocal().getMinX() - control.getLayoutBounds().getMinX());
  double diffMinY=Math.abs(control.getBoundsInLocal().getMinY() - control.getLayoutBounds().getMinY());
  double diffMaxX=Math.abs(control.getBoundsInLocal().getMaxX() - control.getLayoutBounds().getMaxX());
  double diffMaxY=Math.abs(control.getBoundsInLocal().getMaxY() - control.getLayoutBounds().getMaxY());
  Node mask;
switch (getMaskType()) {
case RECT:
    mask=new Rectangle(bounds.getMinX() + diffMinX - snappedLeftInset(),bounds.getMinY() + diffMinY - snappedTopInset(),width - 2 * borderWidth,height - 2 * borderWidth);
  break;
case CIRCLE:
double radius=Math.min((width / 2) - 2 * borderWidth,(height / 2) - 2 * borderWidth);
mask=new Circle((bounds.getMinX() + diffMinX + bounds.getMaxX() - diffMaxX) / 2 - snappedLeftInset(),(bounds.getMinY() + diffMinY + bounds.getMaxY() - diffMaxY) / 2 - snappedTopInset(),radius,Color.BLUE);
break;
case FIT:
mask=new Region();
if (control instanceof Shape) {
((Region)mask).setShape((Shape)control);
}
 else if (control instanceof Region) {
((Region)mask).setShape(((Region)control).getShape());
JFXNodeUtils.updateBackground(((Region)control).getBackground(),(Region)mask);
}
mask.resize(width,height);
mask.relocate(bounds.getMinX() + diffMinX,bounds.getMinY() + diffMinY);
break;
default :
mask=new Rectangle(bounds.getMinX() + diffMinX - snappedLeftInset(),bounds.getMinY() + diffMinY - snappedTopInset(),width - 2 * borderWidth,height - 2 * borderWidth);
break;
}
return mask;
}

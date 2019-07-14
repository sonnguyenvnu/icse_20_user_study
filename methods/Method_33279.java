private void fixPosition(){
  Window w=dialog.getOwner();
  Screen s=com.sun.javafx.util.Utils.getScreen(w);
  Rectangle2D sb=s.getBounds();
  double xR=w.getX() + w.getWidth();
  double xL=w.getX() - dialog.getWidth();
  double x;
  double y;
  if (sb.getMaxX() >= xR + dialog.getWidth()) {
    x=xR;
  }
 else   if (sb.getMinX() <= xL) {
    x=xL;
  }
 else {
    x=Math.max(sb.getMinX(),sb.getMaxX() - dialog.getWidth());
  }
  y=Math.max(sb.getMinY(),Math.min(sb.getMaxY() - dialog.getHeight(),w.getY()));
  dialog.setX(x);
  dialog.setY(y);
}

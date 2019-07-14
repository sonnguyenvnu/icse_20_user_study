@Override public void hideCursor(){
  if (invisibleCursor == null) {
    BufferedImage cursorImg=new BufferedImage(16,16,BufferedImage.TYPE_INT_ARGB);
    Dimension cursorSize=Toolkit.getDefaultToolkit().getBestCursorSize(16,16);
    if (cursorSize.width == 0 || cursorSize.height == 0) {
      invisibleCursor=Cursor.getDefaultCursor();
    }
 else {
      invisibleCursor=canvas.getToolkit().createCustomCursor(cursorImg,new Point(8,8),"blank");
    }
  }
  canvas.setCursor(invisibleCursor);
  cursorVisible=false;
}

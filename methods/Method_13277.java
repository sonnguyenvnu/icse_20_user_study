protected void setCaretPositionAndCenter(int start,int end,Rectangle r){
  if (end != start) {
    try {
      r=r.union(textArea.modelToView(end));
    }
 catch (    BadLocationException e) {
      assert ExceptionUtil.printStackTrace(e);
    }
  }
  Rectangle visible=textArea.getVisibleRect();
  visible.y=r.y - (visible.height - r.height) / 2;
  Rectangle bounds=textArea.getBounds();
  Insets i=textArea.getInsets();
  bounds.y=i.top;
  bounds.height-=i.top + i.bottom;
  if (visible.y < bounds.y) {
    visible.y=bounds.y;
  }
  if (visible.y + visible.height > bounds.y + bounds.height) {
    visible.y=bounds.y + bounds.height - visible.height;
  }
  textArea.scrollRectToVisible(visible);
  textArea.setCaretPosition(start);
}

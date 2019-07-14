private void updateCursor(int mouseX,int mouseY){
  int currentTab=getCurrentCodeIndex();
  for (  Handle n : handles.get(currentTab)) {
    if (n.pick(mouseX,mouseY)) {
      cursorType=Cursor.W_RESIZE_CURSOR;
      setCursor(new Cursor(cursorType));
      return;
    }
  }
  for (  ColorControlBox colorBox : colorBoxes.get(currentTab)) {
    if (colorBox.pick(mouseX,mouseY)) {
      cursorType=Cursor.HAND_CURSOR;
      setCursor(new Cursor(cursorType));
      return;
    }
  }
  if (cursorType == Cursor.W_RESIZE_CURSOR || cursorType == Cursor.HAND_CURSOR || cursorType == -1) {
    cursorType=Cursor.DEFAULT_CURSOR;
    setCursor(new Cursor(cursorType));
  }
}

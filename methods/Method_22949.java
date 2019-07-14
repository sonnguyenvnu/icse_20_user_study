void updateMouse(){
switch (rolloverState) {
case ROLLOVER_CLIPBOARD:
case ROLLOVER_URL:
    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
  break;
case ROLLOVER_COLLAPSE:
setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
break;
case ROLLOVER_NONE:
setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
break;
}
repaint();
}

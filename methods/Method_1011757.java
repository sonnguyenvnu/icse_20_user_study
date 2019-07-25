void dispose(){
  removeListeners();
  JLayeredPane layeredPane=myEditor.getRootPane().getLayeredPane();
  layeredPane.remove(this);
  layeredPane.validate();
  layeredPane.repaint(getBounds());
  myPainter.setGroupHighlighted(myChangeGroup,false);
  myPainter.popupClosed();
  if (myBaseEditor != null) {
    myBaseEditor.dispose();
    myBaseEditor=null;
  }
}

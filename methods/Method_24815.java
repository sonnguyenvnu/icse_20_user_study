public void tweakRemoveListeners(){
  if (baseCompListeners == null) {
    baseCompListeners=painter.getComponentListeners();
    baseMouseListeners=painter.getMouseListeners();
    baseMotionListeners=painter.getMouseMotionListeners();
    baseKeyListeners=editor.getKeyListeners();
  }
  ComponentListener[] componentListeners=painter.getComponentListeners();
  MouseListener[] mouseListeners=painter.getMouseListeners();
  MouseMotionListener[] mouseMotionListeners=painter.getMouseMotionListeners();
  KeyListener[] keyListeners=editor.getKeyListeners();
  for (  ComponentListener cl : componentListeners) {
    painter.removeComponentListener(cl);
  }
  for (  MouseListener ml : mouseListeners) {
    painter.removeMouseListener(ml);
  }
  for (  MouseMotionListener mml : mouseMotionListeners) {
    painter.removeMouseMotionListener(mml);
  }
  for (  KeyListener kl : keyListeners) {
    editor.removeKeyListener(kl);
  }
}

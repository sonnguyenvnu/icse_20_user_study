private void tweakRestoreBaseListeners(){
  for (  ComponentListener cl : baseCompListeners) {
    painter.addComponentListener(cl);
  }
  for (  MouseListener ml : baseMouseListeners) {
    painter.addMouseListener(ml);
  }
  for (  MouseMotionListener mml : baseMotionListeners) {
    painter.addMouseMotionListener(mml);
  }
  for (  KeyListener kl : baseKeyListeners) {
    editor.addKeyListener(kl);
  }
}

protected void initListeners(){
  NEWTMouseListener mouseListener=new NEWTMouseListener();
  window.addMouseListener(mouseListener);
  NEWTKeyListener keyListener=new NEWTKeyListener();
  window.addKeyListener(keyListener);
  NEWTWindowListener winListener=new NEWTWindowListener();
  window.addWindowListener(winListener);
  DrawListener drawlistener=new DrawListener();
  window.addGLEventListener(drawlistener);
}

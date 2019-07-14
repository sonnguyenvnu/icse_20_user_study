protected void addListeners(){
  canvas.addMouseListener(new MouseListener(){
    public void mousePressed(    java.awt.event.MouseEvent e){
      nativeMouseEvent(e);
    }
    public void mouseReleased(    java.awt.event.MouseEvent e){
      nativeMouseEvent(e);
    }
    public void mouseClicked(    java.awt.event.MouseEvent e){
      nativeMouseEvent(e);
    }
    public void mouseEntered(    java.awt.event.MouseEvent e){
      nativeMouseEvent(e);
    }
    public void mouseExited(    java.awt.event.MouseEvent e){
      nativeMouseEvent(e);
    }
  }
);
  canvas.addMouseMotionListener(new MouseMotionListener(){
    public void mouseDragged(    java.awt.event.MouseEvent e){
      nativeMouseEvent(e);
    }
    public void mouseMoved(    java.awt.event.MouseEvent e){
      nativeMouseEvent(e);
    }
  }
);
  canvas.addMouseWheelListener(new MouseWheelListener(){
    public void mouseWheelMoved(    MouseWheelEvent e){
      nativeMouseEvent(e);
    }
  }
);
  canvas.addKeyListener(new KeyListener(){
    public void keyPressed(    java.awt.event.KeyEvent e){
      nativeKeyEvent(e);
    }
    public void keyReleased(    java.awt.event.KeyEvent e){
      nativeKeyEvent(e);
    }
    public void keyTyped(    java.awt.event.KeyEvent e){
      nativeKeyEvent(e);
    }
  }
);
  canvas.addFocusListener(new FocusListener(){
    public void focusGained(    FocusEvent e){
      sketch.focused=true;
      sketch.focusGained();
    }
    public void focusLost(    FocusEvent e){
      sketch.focused=false;
      sketch.focusLost();
    }
  }
);
}

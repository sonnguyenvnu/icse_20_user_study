/** 
 * Set up a listener that will fire proper component resize events in cases where frame.setResizable(true) is called.
 */
private void setupFrameResizeListener(){
  frame.addWindowStateListener(new WindowStateListener(){
    @Override public void windowStateChanged(    WindowEvent e){
      if (Frame.MAXIMIZED_BOTH == e.getNewState()) {
        frame.addNotify();
      }
    }
  }
);
  frame.addComponentListener(new ComponentAdapter(){
    @Override public void componentResized(    ComponentEvent e){
      if (frame.isResizable()) {
        Frame farm=(Frame)e.getComponent();
        if (farm.isVisible()) {
          Dimension windowSize=farm.getSize();
          int x=farm.getX() + currentInsets.left;
          int y=farm.getY() + currentInsets.top;
          int w=windowSize.width - currentInsets.left - currentInsets.right;
          int h=windowSize.height - currentInsets.top - currentInsets.bottom;
          setSize(w / windowScaleFactor,h / windowScaleFactor);
          setLocation(x - currentInsets.left,y - currentInsets.top);
        }
      }
    }
  }
);
}

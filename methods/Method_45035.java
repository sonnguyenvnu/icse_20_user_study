private void adjustWindowPositionBySavedState(){
  Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
  if (!windowPosition.isSavedWindowPositionValid()) {
    final Dimension center=new Dimension((int)(screenSize.width * 0.75),(int)(screenSize.height * 0.75));
    final int x=(int)(center.width * 0.2);
    final int y=(int)(center.height * 0.2);
    this.setBounds(x,y,center.width,center.height);
  }
 else   if (windowPosition.isFullScreen()) {
    int heightMinusTray=screenSize.height;
    if (screenSize.height > 30)     heightMinusTray-=30;
    this.setBounds(0,0,screenSize.width,heightMinusTray);
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    this.addComponentListener(new ComponentAdapter(){
      @Override public void componentResized(      ComponentEvent e){
        if (MainWindow.this.getExtendedState() != JFrame.MAXIMIZED_BOTH) {
          windowPosition.setFullScreen(false);
          if (windowPosition.isSavedWindowPositionValid()) {
            MainWindow.this.setBounds(windowPosition.getWindowX(),windowPosition.getWindowY(),windowPosition.getWindowWidth(),windowPosition.getWindowHeight());
          }
          MainWindow.this.removeComponentListener(this);
        }
      }
    }
);
  }
 else {
    this.setBounds(windowPosition.getWindowX(),windowPosition.getWindowY(),windowPosition.getWindowWidth(),windowPosition.getWindowHeight());
  }
}

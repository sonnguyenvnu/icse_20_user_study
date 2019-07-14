public void readPositionFromWindow(JFrame window){
  isFullScreen=(window.getExtendedState() == JFrame.MAXIMIZED_BOTH);
  if (!isFullScreen) {
    this.readPositionFromComponent(window);
  }
}

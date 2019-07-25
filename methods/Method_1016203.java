/** 
 * Maximizes the Frame.
 */
protected void maximize(){
  if (frame != null) {
    final GraphicsConfiguration gc=frame.getGraphicsConfiguration().getDevice().getDefaultConfiguration();
    frame.setMaximizedBounds(SystemUtils.getMaxWindowBounds(gc,true));
    frame.setExtendedState(Frame.MAXIMIZED_BOTH);
  }
}

/** 
 * Get the bounds rectangle for all displays. 
 */
static Rectangle getDisplaySpan(){
  Rectangle bounds=new Rectangle();
  GraphicsEnvironment environment=GraphicsEnvironment.getLocalGraphicsEnvironment();
  for (  GraphicsDevice device : environment.getScreenDevices()) {
    for (    GraphicsConfiguration config : device.getConfigurations()) {
      Rectangle2D.union(bounds,config.getBounds(),bounds);
    }
  }
  return bounds;
}

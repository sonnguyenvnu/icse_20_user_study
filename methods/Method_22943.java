/** 
 * Returns a GraphicsConfiguration so that a new Editor Frame can be constructed. First tries to match the bounds for this state information to an existing config (nominally, a display) and if that doesn't work, then returns the default configuration/default display.
 */
GraphicsConfiguration checkConfig(){
  if (deviceBounds != null) {
    GraphicsEnvironment graphicsEnvironment=GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice[] screenDevices=graphicsEnvironment.getScreenDevices();
    for (    GraphicsDevice device : screenDevices) {
      GraphicsConfiguration[] configurations=device.getConfigurations();
      for (      GraphicsConfiguration config : configurations) {
        if (config.getBounds().equals(deviceBounds)) {
          return config;
        }
      }
    }
  }
  return defaultConfig();
}

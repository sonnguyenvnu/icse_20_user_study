GraphicsConfiguration defaultConfig(){
  GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
  GraphicsDevice device=ge.getDefaultScreenDevice();
  GraphicsConfiguration config=device.getDefaultConfiguration();
  deviceBounds=config.getBounds();
  return config;
}

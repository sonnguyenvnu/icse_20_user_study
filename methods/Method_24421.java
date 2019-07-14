protected void initDisplay(){
  display=NewtFactory.createDisplay(null);
  display.addReference();
  screen=NewtFactory.createScreen(display,0);
  screen.addReference();
  GraphicsEnvironment environment=GraphicsEnvironment.getLocalGraphicsEnvironment();
  GraphicsDevice[] awtDevices=environment.getScreenDevices();
  GraphicsDevice awtDisplayDevice=null;
  int displayNum=sketch.sketchDisplay();
  if (displayNum > 0) {
    if (displayNum <= awtDevices.length) {
      awtDisplayDevice=awtDevices[displayNum - 1];
    }
 else {
      System.err.format("Display %d does not exist, " + "using the default display instead.%n",displayNum);
      for (int i=0; i < awtDevices.length; i++) {
        System.err.format("Display %d is %s%n",i + 1,awtDevices[i]);
      }
    }
  }
 else   if (0 < awtDevices.length) {
    awtDisplayDevice=awtDevices[0];
  }
  if (awtDisplayDevice == null) {
    awtDisplayDevice=GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
  }
  displayRect=awtDisplayDevice.getDefaultConfiguration().getBounds();
}

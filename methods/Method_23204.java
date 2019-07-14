/** 
 * @param display the display number to check
 */
public int displayDensity(int display){
  if (PApplet.platform == PConstants.MACOSX) {
    final String javaVendor=System.getProperty("java.vendor");
    if (javaVendor.contains("Oracle")) {
      GraphicsDevice device;
      GraphicsEnvironment env=GraphicsEnvironment.getLocalGraphicsEnvironment();
      if (display == -1) {
        device=env.getDefaultScreenDevice();
      }
 else       if (display == SPAN) {
        throw new RuntimeException("displayDensity() only works with specific display numbers");
      }
 else {
        GraphicsDevice[] devices=env.getScreenDevices();
        if (display > 0 && display <= devices.length) {
          device=devices[display - 1];
        }
 else {
          if (devices.length == 1) {
            System.err.println("Only one display is currently known, use displayDensity(1).");
          }
 else {
            System.err.format("Your displays are numbered %d through %d, " + "pass one of those numbers to displayDensity()%n",1,devices.length);
          }
          throw new RuntimeException("Display " + display + " does not exist.");
        }
      }
      try {
        Field field=device.getClass().getDeclaredField("scale");
        if (field != null) {
          field.setAccessible(true);
          Object scale=field.get(device);
          if (scale instanceof Integer && ((Integer)scale).intValue() == 2) {
            return 2;
          }
        }
      }
 catch (      Exception ignore) {
      }
    }
  }
 else   if (PApplet.platform == PConstants.WINDOWS || PApplet.platform == PConstants.LINUX) {
    if (suggestedDensity == -1) {
      return 1;
    }
 else     if (suggestedDensity == 1 || suggestedDensity == 2) {
      return suggestedDensity;
    }
  }
  return 1;
}

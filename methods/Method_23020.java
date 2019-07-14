static private boolean checkRetina(){
  if (Platform.isMacOS()) {
    GraphicsEnvironment env=GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice device=env.getDefaultScreenDevice();
    try {
      Field field=device.getClass().getDeclaredField("scale");
      if (field != null) {
        field.setAccessible(true);
        Object scale=field.get(device);
        if (scale instanceof Integer && ((Integer)scale).intValue() == 2) {
          return true;
        }
      }
    }
 catch (    Exception ignore) {
    }
  }
  return false;
}

/** 
 * Creates  {@link JoddJoy}. This is a place where to configure the app.
 */
protected JoddJoy createJoy(){
  final JoddJoy joy=JoddJoy.get();
  if (SystemUtil.info().isAtLeastJavaVersion(9)) {
    joy.withScanner(joyScanner -> joyScanner.scanClasspathOf(this.getClass()));
  }
  return joy;
}

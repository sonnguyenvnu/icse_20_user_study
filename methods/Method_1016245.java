/** 
 * Initializes StyleManager settings.
 */
public static synchronized void initialize(){
  if (!initialized) {
    initialized=true;
    applyDefaultSkin();
  }
}

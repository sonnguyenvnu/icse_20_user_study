/** 
 * Should use animations
 */
public static boolean animationsEnabled(){
  return !getPrefs().get(Keys.ANIMATIONS_DISABLED,Defaults.ANIMATIONS_DISABLED);
}

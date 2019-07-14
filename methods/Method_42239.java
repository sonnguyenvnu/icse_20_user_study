/** 
 * Should show the Emoji Easter Egg.
 */
public static boolean showEasterEgg(){
  return getPrefs().get(Keys.SHOW_EASTER_EGG,Defaults.SHOW_EASTER_EGG);
}

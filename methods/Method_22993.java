/** 
 * Standardized width for buttons. Mac OS X 10.3 wants 70 as its default, Windows XP needs 66, and my Ubuntu machine needs 80+, so 80 seems proper. This is now stored in the languages file since this may need to be larger for languages that are consistently wider than English.
 */
static public int getButtonWidth(){
  return zoom(Integer.parseInt(Language.text("preferences.button.width")));
}

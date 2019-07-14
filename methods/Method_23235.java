/** 
 * Initialize the Look & Feel if it hasn't been already. Call this before using any Swing-related code in PApplet methods.
 */
static private void checkLookAndFeel(){
  if (!lookAndFeelCheck) {
    if (platform == WINDOWS) {
      try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      }
 catch (      Exception e) {
      }
    }
    lookAndFeelCheck=true;
  }
}

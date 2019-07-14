/** 
 * Give this Frame the Processing icon set. Ignored on OS X, because they thought different and made this function set the minified image of the window, not the window icon for the dock or cmd-tab.
 */
static public void setIcon(Window window){
  if (!Platform.isMacOS()) {
    if (iconImages == null) {
      iconImages=new ArrayList<>();
      final int[] sizes={16,32,48,64,128,256,512};
      for (      int sz : sizes) {
        iconImages.add(Toolkit.getLibImage("icons/pde-" + sz + ".png"));
      }
    }
    window.setIconImages(iconImages);
  }
}

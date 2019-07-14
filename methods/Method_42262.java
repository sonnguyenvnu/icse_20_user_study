/** 
 * Set the theme for this Navigation Drawer.
 * @param primaryColor    Color for header background
 * @param backgroundColor Color for drawer background
 * @param textColor       Color for item text
 * @param iconColor       Color for icons
 */
public void setTheme(@ColorInt int primaryColor,@ColorInt int backgroundColor,@ColorInt int textColor,@ColorInt int iconColor){
  setBackgroundColor(backgroundColor);
  drawerHeader.setBackgroundColor(primaryColor);
  for (  NavigationEntry navigationEntry : navigationEntries) {
    navigationEntry.setTextColor(textColor);
    navigationEntry.setIconColor(iconColor);
  }
}

/** 
 * Swing menus are looking pretty bad on Linux when the GTK LaF is used (See bug #6925412). It will most likely never be fixed anytime soon so this method provides a workaround for it. It uses reflection to change the GTK style objects of Swing so popup menu borders have a minimum thickness of 1 and menu separators have a minimum vertical thickness of 1.
 */
public static void installGtkPopupBugWorkaround(){
  LookAndFeel laf=UIManager.getLookAndFeel();
  Class<?> lafClass=laf.getClass();
  if (!lafClass.getName().equals("com.sun.java.swing.plaf.gtk.GTKLookAndFeel"))   return;
  try {
    Field field=lafClass.getDeclaredField("styleFactory");
    boolean accessible=field.isAccessible();
    field.setAccessible(true);
    Object styleFactory=field.get(laf);
    field.setAccessible(accessible);
    Object style=getGtkStyle(styleFactory,new JPopupMenu(),"POPUP_MENU");
    fixGtkThickness(style,"yThickness");
    fixGtkThickness(style,"xThickness");
    style=getGtkStyle(styleFactory,new JSeparator(),"POPUP_MENU_SEPARATOR");
    fixGtkThickness(style,"yThickness");
  }
 catch (  Exception e) {
    assert ExceptionUtil.printStackTrace(e);
  }
}

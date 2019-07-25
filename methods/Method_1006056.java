/** 
 * Perform initializations that are only used in graphical mode. This is to prevent the "Xlib: connection to ":0.0" refused by server" error when access to the X server on Un*x is unavailable.
 */
public static void init(){
  if (Globals.prefs.getBoolean(JabRefPreferences.EDITOR_EMACS_KEYBINDINGS)) {
    EmacsKeyBindings.load();
  }
  GUIGlobals.updateEntryEditorColors();
  IconTheme.loadFonts();
  GUIGlobals.currentFont=new Font(Globals.prefs.get(JabRefPreferences.FONT_FAMILY),Globals.prefs.getInt(JabRefPreferences.FONT_STYLE),Globals.prefs.getInt(JabRefPreferences.FONT_SIZE));
}

/** 
 * Restores the original keybindings for the concrete subclasses of {@link JTextComponent}.
 */
public static void unload(){
  for (int i=0; i < EmacsKeyBindings.JTCS.length; i++) {
    Keymap backup=JTextComponent.getKeymap(EmacsKeyBindings.JTCS[i].getClass().getName());
    if (backup != null) {
      Keymap current=EmacsKeyBindings.JTCS[i].getKeymap();
      current.removeBindings();
      Action[] bound=backup.getBoundActions();
      for (      Action aBound : bound) {
        KeyStroke[] strokes=backup.getKeyStrokesForAction(bound[i]);
        for (        KeyStroke stroke : strokes) {
          current.addActionForKeyStroke(stroke,aBound);
        }
      }
      current.setDefaultAction(backup.getDefaultAction());
    }
  }
}

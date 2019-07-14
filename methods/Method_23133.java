/** 
 * Registers key events for a Ctrl-W and ESC with an ActionListener that will take care of disposing the window.
 */
static public void registerWindowCloseKeys(JRootPane root,ActionListener disposer){
  KeyStroke stroke=KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0);
  root.registerKeyboardAction(disposer,stroke,JComponent.WHEN_IN_FOCUSED_WINDOW);
  int modifiers=Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
  stroke=KeyStroke.getKeyStroke('W',modifiers);
  root.registerKeyboardAction(disposer,stroke,JComponent.WHEN_IN_FOCUSED_WINDOW);
}

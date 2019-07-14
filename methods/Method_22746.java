/** 
 * Adds a key binding to this input handler. The key binding is a list of white space separated key strokes of the form <i>[modifiers+]key</i> where modifier is C for Control, A for Alt, or S for Shift, and key is either a character (a-z) or a field name in the KeyEvent class prefixed with VK_ (e.g., BACK_SPACE)
 * @param keyBinding The key binding
 * @param action The action
 */
public void addKeyBinding(String keyBinding,ActionListener action){
  Map current=bindings;
  StringTokenizer st=new StringTokenizer(keyBinding);
  while (st.hasMoreTokens()) {
    KeyStroke keyStroke=parseKeyStroke(st.nextToken());
    if (keyStroke == null)     return;
    if (st.hasMoreTokens()) {
      Object o=current.get(keyStroke);
      if (o instanceof Map)       current=(Map)o;
 else {
        o=new HashMap();
        current.put(keyStroke,o);
        current=(Map)o;
      }
    }
 else     current.put(keyStroke,action);
  }
}

/** 
 * Converts a string to a keystroke. The string should be of the form <i>modifiers</i>+<i>shortcut</i> where <i>modifiers</i> is any combination of A for Alt, C for Control, S for Shift or M for Meta, and <i>shortcut</i> is either a single character, or a keycode name from the <code>KeyEvent</code> class, without the <code>VK_</code> prefix.
 * @param keyStroke A string description of the key stroke
 */
public static KeyStroke parseKeyStroke(String keyStroke){
  if (keyStroke == null)   return null;
  int modifiers=0;
  int index=keyStroke.indexOf('+');
  if (index != -1) {
    for (int i=0; i < index; i++) {
switch (Character.toUpperCase(keyStroke.charAt(i))) {
case 'A':
        modifiers|=InputEvent.ALT_MASK;
      break;
case 'C':
    modifiers|=InputEvent.CTRL_MASK;
  break;
case 'M':
modifiers|=InputEvent.META_MASK;
break;
case 'S':
modifiers|=InputEvent.SHIFT_MASK;
break;
}
}
}
String key=keyStroke.substring(index + 1);
if (key.length() == 1) {
char ch=Character.toUpperCase(key.charAt(0));
if (modifiers == 0) return KeyStroke.getKeyStroke(ch);
 else return KeyStroke.getKeyStroke(ch,modifiers);
}
 else if (key.length() == 0) {
System.err.println("Invalid key stroke: " + keyStroke);
return null;
}
 else {
int ch;
try {
ch=KeyEvent.class.getField("VK_".concat(key)).getInt(null);
}
 catch (Exception e) {
System.err.println("Invalid key stroke: " + keyStroke);
return null;
}
return KeyStroke.getKeyStroke(ch,modifiers);
}
}

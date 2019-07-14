private void parseAction(String name,int shortcutAction,Properties props){
  String value=props.getProperty(name);
  if (value == null)   return;
  String[] parts=value.toLowerCase().trim().split("\\+");
  String input=parts.length == 2 ? parts[1].trim() : null;
  if (!(parts.length == 2 && parts[0].trim().equals("ctrl")) || input.isEmpty() || input.length() > 2) {
    Log.e("termux","Keyboard shortcut '" + name + "' is not Ctrl+<something>");
    return;
  }
  char c=input.charAt(0);
  int codePoint=c;
  if (Character.isLowSurrogate(c)) {
    if (input.length() != 2 || Character.isHighSurrogate(input.charAt(1))) {
      Log.e("termux","Keyboard shortcut '" + name + "' is not Ctrl+<something>");
      return;
    }
 else {
      codePoint=Character.toCodePoint(input.charAt(1),c);
    }
  }
  shortcuts.add(new KeyboardShortcut(codePoint,shortcutAction));
}

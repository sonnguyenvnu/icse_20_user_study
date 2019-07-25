public final void init(){
  Keymap keymap=getKeymap();
  if (keymap == null) {
    return;
  }
  for (  Entry<String,Set<ShortcutWrapper>> e : mySimpleShortcuts.entrySet()) {
    String key=e.getKey();
    for (    ShortcutWrapper s : e.getValue()) {
      addShortcutToKeymap(key,keymap,s.myShortcut,s.myRemove,s.myReplaceAll);
    }
  }
}

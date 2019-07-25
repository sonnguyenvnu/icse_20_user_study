public final void dispose(){
  Keymap keymap=getKeymap();
  if (keymap == null) {
    return;
  }
  for (  Entry<String,Set<Shortcut>> e : myAddedComplexShortcuts.entrySet()) {
    String key=e.getKey();
    for (    Shortcut s : e.getValue()) {
      keymap.removeShortcut(key,s);
    }
  }
  myAddedComplexShortcuts.clear();
  for (  Entry<String,Set<ShortcutWrapper>> e : mySimpleShortcuts.entrySet()) {
    String key=e.getKey();
    for (    ShortcutWrapper s : e.getValue()) {
      keymap.removeShortcut(key,s.myShortcut);
    }
  }
  mySimpleShortcuts.clear();
  for (  Entry<String,Set<Shortcut>> e : myRemovedShortcuts.entrySet()) {
    String key=e.getKey();
    for (    Shortcut s : e.getValue()) {
      keymap.addShortcut(key,s);
    }
  }
  myRemovedShortcuts.clear();
  ourClearedActions.clear();
}

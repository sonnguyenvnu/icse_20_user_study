public final void register(){
  if (myProject.isDisposed()) {
    return;
  }
  if (isRegistered()) {
    return;
  }
  ThreadUtils.assertEDT();
  myIsRegistered=true;
  myWindowManager=ToolWindowManager.getInstance(myProject);
  if (myShortcutsByKeymap != null) {
    String actionId=ActivateToolWindowAction.getActionIdForToolWindow(myId);
    List<Keymap> keymaps=new ArrayList<>(myShortcutsByKeymap.size());
    for (    Entry<String,KeyStroke> keymapItem : myShortcutsByKeymap.entrySet()) {
      Keymap keymap=KeymapManager.getInstance().getKeymap(keymapItem.getKey());
      if (keymap == null) {
        LOG.warn("Keymap " + keymapItem.getKey() + " cannot be found");
        return;
      }
      keymaps.add(keymap);
    }
    Collections.sort(keymaps,(o1,o2) -> {
      for (Keymap parent=o1.getParent(); parent != null; parent=parent.getParent()) {
        if (parent.equals(o2)) {
          return 1;
        }
      }
      for (Keymap parent=o2.getParent(); parent != null; parent=parent.getParent()) {
        if (parent.equals(o1)) {
          return -1;
        }
      }
      return 0;
    }
);
    for (    Keymap keymap : keymaps) {
      KeyboardShortcut defShortcut=new KeyboardShortcut(myShortcutsByKeymap.get(keymap.getName()),null);
      keymap.removeAllActionShortcuts(actionId);
      keymap.addShortcut(actionId,defShortcut);
    }
  }
  ToolWindow toolWindow=myWindowManager.getToolWindow(myId);
  if (toolWindow == null) {
    toolWindow=myWindowManager.registerToolWindow(myId,myCanCloseContent,myAnchor,getProject(),true,mySideTool);
  }
  if (myIcon != null) {
    toolWindow.setIcon(myIcon);
  }
  toolWindow.setToHideOnEmptyContent(true);
  toolWindow.installWatcher(toolWindow.getContentManager());
  setAvailable(isInitiallyAvailable());
  doRegister();
  UiNotifyConnector.doWhenFirstShown(toolWindow.getComponent(),() -> {
    if (myComponent == null) {
      myComponent=getComponent();
    }
    if (myComponent != null) {
      addContent(myComponent,"",null,false);
    }
  }
);
}

private void initialize(){
  setWebColoredBackground(false);
  container=new WebPanel();
  container.setLayout(new ComponentPanelLayout());
  add(container,BorderLayout.CENTER);
  final HotkeyRunnable prevAction=new HotkeyRunnable(){
    @Override public void run(    final KeyEvent e){
      if (upDownHotkeysAllowed && Hotkey.UP.isTriggered(e) || leftRightHotkeysAllowed && Hotkey.LEFT.isTriggered(e)) {
        final int index=getFocusedElementIndex();
        if (index == -1) {
          focusElement(getElementCount() - 1);
        }
 else {
          focusElement(index > 0 ? index - 1 : getElementCount() - 1);
        }
      }
    }
  }
;
  HotkeyManager.registerHotkey(this,this,Hotkey.UP,prevAction);
  HotkeyManager.registerHotkey(this,this,Hotkey.LEFT,prevAction);
  final HotkeyRunnable nextAction=new HotkeyRunnable(){
    @Override public void run(    final KeyEvent e){
      if (upDownHotkeysAllowed && Hotkey.DOWN.isTriggered(e) || leftRightHotkeysAllowed && Hotkey.RIGHT.isTriggered(e)) {
        final int index=getFocusedElementIndex();
        if (index == -1) {
          focusElement(0);
        }
 else {
          focusElement(index < getElementCount() - 1 ? index + 1 : 0);
        }
      }
    }
  }
;
  HotkeyManager.registerHotkey(this,this,Hotkey.DOWN,nextAction);
  HotkeyManager.registerHotkey(this,this,Hotkey.RIGHT,nextAction);
}

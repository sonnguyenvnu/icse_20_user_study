@Override public boolean onKeyDown(int keyCode,KeyEvent e,TerminalSession currentSession){
  if (handleVirtualKeys(keyCode,e,true))   return true;
  if (keyCode == KeyEvent.KEYCODE_ENTER && !currentSession.isRunning()) {
    mActivity.removeFinishedSession(currentSession);
    return true;
  }
 else   if (e.isCtrlPressed() && e.isAltPressed()) {
    int unicodeChar=e.getUnicodeChar(0);
    if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN || unicodeChar == 'n') {
      mActivity.switchToSession(true);
    }
 else     if (keyCode == KeyEvent.KEYCODE_DPAD_UP || unicodeChar == 'p') {
      mActivity.switchToSession(false);
    }
 else     if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
      mActivity.getDrawer().openDrawer(Gravity.LEFT);
    }
 else     if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
      mActivity.getDrawer().closeDrawers();
    }
 else     if (unicodeChar == 'k') {
      InputMethodManager imm=(InputMethodManager)mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
    }
 else     if (unicodeChar == 'm') {
      mActivity.mTerminalView.showContextMenu();
    }
 else     if (unicodeChar == 'r') {
      mActivity.renameSession(currentSession);
    }
 else     if (unicodeChar == 'c') {
      mActivity.addNewSession(false,null);
    }
 else     if (unicodeChar == 'u') {
      mActivity.showUrlSelection();
    }
 else     if (unicodeChar == 'v') {
      mActivity.doPaste();
    }
 else     if (unicodeChar == '+' || e.getUnicodeChar(KeyEvent.META_SHIFT_ON) == '+') {
      mActivity.changeFontSize(true);
    }
 else     if (unicodeChar == '-') {
      mActivity.changeFontSize(false);
    }
 else     if (unicodeChar >= '1' && unicodeChar <= '9') {
      int num=unicodeChar - '1';
      TermuxService service=mActivity.mTermService;
      if (service.getSessions().size() > num)       mActivity.switchToSession(service.getSessions().get(num));
    }
    return true;
  }
  return false;
}

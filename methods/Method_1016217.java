/** 
 * {@inheritDoc}
 */
@Override public void update(final AbstractButton c,final String key,final Value value,final Object... data){
  final String text=getDefaultText(value,data);
  c.setText(text != null ? text : null);
  c.setMnemonic(text != null && value.getMnemonic() != null ? value.getMnemonic() : 0);
  if (isHotkeyCached(c)) {
    HotkeyManager.unregisterHotkey(getCachedHotkey(c));
  }
  if (value.getHotkey() != null) {
    final KeyStroke keyStroke=KeyStroke.getKeyStroke(value.getHotkey());
    if (keyStroke != null) {
      final HotkeyData hotkeyData=SwingUtils.getHotkeyData(keyStroke);
      final HotkeyInfo hotkeyInfo=HotkeyManager.registerHotkey(c,hotkeyData);
      cacheHotkey(c,hotkeyInfo);
    }
  }
}

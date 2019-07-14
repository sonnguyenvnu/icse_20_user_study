private boolean registerExtension(String extension) throws UnsupportedEncodingException {
  final REGISTRY_ROOT_KEY rootKey=REGISTRY_ROOT_KEY.CURRENT_USER;
  final String docPrefix="Software\\Classes\\" + REG_DOC;
  return (WindowsRegistry.createKey(rootKey,"Software\\Classes",extension) && WindowsRegistry.setStringValue(rootKey,"Software\\Classes\\" + extension,"",REG_DOC) && WindowsRegistry.createKey(rootKey,"Software\\Classes",REG_DOC) && WindowsRegistry.setStringValue(rootKey,docPrefix,"",APP_NAME + " Source Code") && WindowsRegistry.createKey(rootKey,docPrefix,"shell") && WindowsRegistry.createKey(rootKey,docPrefix + "\\shell","open") && WindowsRegistry.createKey(rootKey,docPrefix + "\\shell\\open","command") && WindowsRegistry.setStringValue(rootKey,docPrefix + "\\shell\\open\\command","",REG_OPEN_COMMAND));
}

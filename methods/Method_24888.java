static public boolean hasSettingsMethod(String code){
  final String uncommented=scrubComments(code);
  return findInCurrentScope(VOID_SETTINGS_REGEX,uncommented) != null;
}

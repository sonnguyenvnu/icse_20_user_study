/** 
 * Make sure that .pde files are associated with processing.exe.
 */
protected void checkAssociations(){
  try {
    if (Preferences.getBoolean("platform.auto_file_type_associations")) {
      String knownCommand=WindowsRegistry.getStringValue(REGISTRY_ROOT_KEY.CURRENT_USER,"Software\\Classes\\" + REG_DOC + "\\shell\\open\\command","");
      if (knownCommand == null || !knownCommand.equals(REG_OPEN_COMMAND)) {
        setAssociations();
      }
 else {
        for (        String extension : APP_EXTENSIONS) {
          if (!WindowsRegistry.valueExists(REGISTRY_ROOT_KEY.CURRENT_USER,"Software\\Classes",extension)) {
            setAssociations();
          }
        }
      }
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
}

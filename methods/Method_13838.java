public static String getExtensionFilePath(){
  File dir=((FileProjectManager)ProjectManager.singleton).getWorkspaceDir();
  String fileSep=System.getProperty("file.separator");
  String filename=dir.getPath() + fileSep + DATABASE_EXTENSION_DIR + fileSep + SETTINGS_FILE_NAME;
  return filename;
}

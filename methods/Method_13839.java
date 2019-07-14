public static String getExtensionFolder(){
  File dir=((FileProjectManager)ProjectManager.singleton).getWorkspaceDir();
  String fileSep=System.getProperty("file.separator");
  String filename=dir.getPath() + fileSep + DATABASE_EXTENSION_DIR;
  return filename;
}

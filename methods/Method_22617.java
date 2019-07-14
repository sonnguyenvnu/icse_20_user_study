/** 
 * Create a fresh applet/application folder if the 'delete target folder' pref has been set in the preferences.
 */
public void prepareExportFolder(File targetFolder){
  if (targetFolder != null) {
    if (Preferences.getBoolean("export.delete_target_folder")) {
      if (targetFolder.exists()) {
        try {
          Platform.deleteFile(targetFolder);
        }
 catch (        IOException e) {
          e.printStackTrace();
        }
      }
    }
    targetFolder.mkdirs();
  }
}

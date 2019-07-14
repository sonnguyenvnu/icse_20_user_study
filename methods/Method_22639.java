/** 
 * Associate .pde files with this version of Processing. After 2.0.1, this was changed to only set the values for the current user, so that it would no longer silently fail on systems that have UAC turned on.
 */
protected void setAssociations() throws UnsupportedEncodingException {
  for (  String extension : APP_EXTENSIONS) {
    if (!registerExtension(extension)) {
      Messages.log("Could not associate " + extension + "files, " + "turning off auto-associate pref.");
      Preferences.setBoolean("platform.auto_file_type_associations",false);
    }
  }
}

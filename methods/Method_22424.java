/** 
 * Convenience method to get a File object for the specified filename inside the settings folder. Used to get preferences and recent sketch files.
 * @param filename A file inside the settings folder.
 * @return filename wrapped as a File object inside the settings folder
 */
static public File getSettingsFile(String filename){
  return new File(getSettingsFolder(),filename);
}

/** 
 * Get number of folder columns to display in Landscape orientation
 */
public static int getFolderColumnsLandscape(){
  return getPrefs().get(Keys.FOLDER_COLUMNS_LANDSCAPE,Defaults.FOLDER_COLUMNS_LANDSCAPE);
}

/** 
 * Create the data folder if it does not exist already. As a convenience, it also returns the data folder, since it's likely about to be used.
 */
public File prepareDataFolder(){
  if (!dataFolder.exists()) {
    dataFolder.mkdirs();
  }
  return dataFolder;
}

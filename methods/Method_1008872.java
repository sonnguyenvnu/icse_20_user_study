/** 
 * Reads the default font cache file and returns its contents.
 * @return the font cache deserialized from the file (or null if no cache file exists or ifit could not be read)
 */
public static FontCache load(){
  return loadFrom(getDefaultCacheFile(false));
}

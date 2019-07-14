/** 
 * Checks whether this denotes a location on the filesystem.
 * @return {@code true} if it does, {@code false} if it doesn't.
 */
public boolean isFileSystem(){
  return FILESYSTEM_PREFIX.equals(prefix);
}

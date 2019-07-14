/** 
 * Checks whether this location is a parent of this other location.
 * @param other The other location.
 * @return {@code true} if it is, {@code false} if it isn't.
 */
@SuppressWarnings("SimplifiableIfStatement") public boolean isParentOf(Location other){
  if (isClassPath() && other.isClassPath()) {
    return (other.getDescriptor() + "/").startsWith(getDescriptor() + "/");
  }
  if (isFileSystem() && other.isFileSystem()) {
    return (other.getPath() + File.separator).startsWith(getDescriptor() + File.separator);
  }
  return false;
}

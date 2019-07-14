/** 
 * Returns the index of the last directory separator character. <p> This method will handle a file in either Unix or Windows format. The position of the last forward or backslash is returned. <p> The output will be the same irrespective of the machine that the code is running on.
 * @param filename  the filename to find the last path separator in, null returns -1
 * @return the index of the last separator character, or -1 if there is no such character
 */
public static int indexOfLastSeparator(final String filename){
  if (filename == null) {
    return -1;
  }
  int lastUnixPos=filename.lastIndexOf(UNIX_SEPARATOR);
  int lastWindowsPos=filename.lastIndexOf(WINDOWS_SEPARATOR);
  return Math.max(lastUnixPos,lastWindowsPos);
}

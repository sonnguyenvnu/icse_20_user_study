/** 
 * Returns the index of the last extension separator character, which is a dot. <p> This method also checks that there is no directory separator after the last dot. To do this it uses  {@link #indexOfLastSeparator(String)} which willhandle a file in either Unix or Windows format. <p> The output will be the same irrespective of the machine that the code is running on.
 * @param filename  the filename to find the last path separator in, null returns -1
 * @return the index of the last separator character, or -1 if thereis no such character
 */
public static int indexOfExtension(final String filename){
  if (filename == null) {
    return -1;
  }
  int extensionPos=filename.lastIndexOf(EXTENSION_SEPARATOR);
  int lastSeparator=indexOfLastSeparator(filename);
  return (lastSeparator > extensionPos ? -1 : extensionPos);
}

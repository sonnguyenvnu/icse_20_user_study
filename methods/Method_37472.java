/** 
 * Gets the extension of a filename. <p> This method returns the textual part of the filename after the last dot. There must be no directory separator after the dot. <pre> {@code foo.txt      --> "txt" a/b/c.jpg    --> "jpg" a/b.txt/c    --> "" a/b/c        --> ""}</pre> <p> The output will be the same irrespective of the machine that the code is running on.
 * @param filename the filename to retrieve the extension of.
 * @return the extension of the file or an empty string if none exists.
 */
public static String getExtension(final String filename){
  if (filename == null) {
    return null;
  }
  int index=indexOfExtension(filename);
  if (index == -1) {
    return StringPool.EMPTY;
  }
 else {
    return filename.substring(index + 1);
  }
}

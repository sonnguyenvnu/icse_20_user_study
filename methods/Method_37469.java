/** 
 * Gets the full path from a full filename, which is the prefix + path. <p> This method will handle a file in either Unix or Windows format. The method is entirely text based, and returns the text before and including the last forward or backslash. <pre> {@code C:\a\b\c.txt --> C:\a\b\ ~/a/b/c.txt  --> ~/a/b/ a.txt        --> "" a/b/c        --> a/b/ a/b/c/       --> a/b/c/ C:           --> C: C:\          --> C:\ ~            --> ~/ ~/           --> ~/ ~user        --> ~user/ ~user/       --> ~user/}</pre> <p> The output will be the same irrespective of the machine that the code is running on.
 * @param filename  the filename to query, null returns null
 * @return the path of the file, an empty string if none exists, null if invalid
 */
public static String getFullPath(final String filename){
  return doGetFullPath(filename,true);
}

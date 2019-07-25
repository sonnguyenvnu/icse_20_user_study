/** 
 * Creates a  {@link FileLocation} instance for the specified local file.
 * @param file A local file.
 * @return The file's location.
 */
public static FileLocation create(File file){
  return new FileFileLocation(file);
}

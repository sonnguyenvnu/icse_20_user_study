/** 
 * Open the view file located at the same location as the provided class.
 * @param clazz the class determining the location of the view file
 * @return an {@link InputStream} representing the view file
 */
public static InputStream view(final Class clazz){
  return openStream(clazz,VIEW);
}

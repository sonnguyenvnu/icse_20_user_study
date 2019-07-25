/** 
 * Open the schema file located at the same location as the provided class.
 * @param clazz the class determining the location of the schema file
 * @return an {@link InputStream} representing the schema file
 */
public static InputStream schema(final Class clazz){
  return openStream(clazz,SCHEMA);
}

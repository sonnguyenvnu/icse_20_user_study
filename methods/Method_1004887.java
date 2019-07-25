/** 
 * Open the schema files located at the same location as the provided class.
 * @param clazz the class determining the location of the schema files
 * @return an array of {@link InputStream}s representing the schema files
 */
public static InputStream[] schemas(final Class clazz){
  return openStreams(clazz,SCHEMA_FOLDER);
}

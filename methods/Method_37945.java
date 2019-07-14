/** 
 * Create properties from the file.
 * @param file properties file to load
 */
public static Properties createFromFile(final File file) throws IOException {
  Properties prop=new Properties();
  loadFromFile(prop,file);
  return prop;
}

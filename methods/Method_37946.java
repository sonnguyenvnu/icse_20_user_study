/** 
 * Creates properties from string.
 */
public static Properties createFromString(final String data) throws IOException {
  Properties p=new Properties();
  loadFromString(p,data);
  return p;
}

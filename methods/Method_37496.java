/** 
 * @see #appendString(File,String,String)
 */
public static void appendString(final String dest,final String data,final String encoding) throws IOException {
  appendString(file(dest),data,encoding);
}

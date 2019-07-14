/** 
 * @see #appendString(File,String,String)
 */
public static void appendString(final File dest,final String data) throws IOException {
  appendString(dest,data,encoding());
}

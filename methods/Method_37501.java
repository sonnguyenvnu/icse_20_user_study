/** 
 * Appends bytes. append = true
 * @see #outBytes(File,byte[],int,int,boolean)
 */
public static void appendBytes(final File dest,final byte[] data,final int off,final int len) throws IOException {
  outBytes(dest,data,off,len,true);
}

/** 
 * Writes serializable object to a file. Existing file will be overwritten.
 */
public static void writeObject(final File dest,final Object object) throws IOException {
  FileOutputStream fos=null;
  BufferedOutputStream bos=null;
  ObjectOutputStream oos=null;
  try {
    fos=new FileOutputStream(dest);
    bos=new BufferedOutputStream(fos);
    oos=new ObjectOutputStream(bos);
    oos.writeObject(object);
  }
  finally {
    StreamUtil.close(oos);
    StreamUtil.close(bos);
    StreamUtil.close(fos);
  }
}

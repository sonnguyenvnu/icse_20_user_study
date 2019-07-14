/** 
 * Create object copy using serialization mechanism.
 */
public static <T extends Serializable>T cloneViaSerialization(final T obj) throws IOException, ClassNotFoundException {
  FastByteArrayOutputStream bos=new FastByteArrayOutputStream();
  ObjectOutputStream out=null;
  ObjectInputStream in=null;
  Object objCopy=null;
  try {
    out=new ObjectOutputStream(bos);
    out.writeObject(obj);
    out.flush();
    byte[] bytes=bos.toByteArray();
    in=new ObjectInputStream(new ByteArrayInputStream(bytes));
    objCopy=in.readObject();
  }
  finally {
    StreamUtil.close(out);
    StreamUtil.close(in);
  }
  return (T)objCopy;
}

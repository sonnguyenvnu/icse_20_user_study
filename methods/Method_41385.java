/** 
 * <p> Create a serialized <code>java.util.ByteArrayOutputStream</code> version of an Object. </p>
 * @param obj the object to serialize
 * @return the serialized ByteArrayOutputStream
 * @throws IOException if serialization causes an error
 */
protected ByteArrayOutputStream serializeObject(Object obj) throws IOException {
  ByteArrayOutputStream baos=new ByteArrayOutputStream();
  if (null != obj) {
    ObjectOutputStream out=new ObjectOutputStream(baos);
    out.writeObject(obj);
    out.flush();
  }
  return baos;
}

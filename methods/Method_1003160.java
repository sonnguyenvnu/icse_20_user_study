/** 
 * De-serialize the byte array to an object.
 * @param data the byte array
 * @return the object
 */
public static Object deserialize(byte[] data){
  try {
    ByteArrayInputStream in=new ByteArrayInputStream(data);
    ObjectInputStream is=new ObjectInputStream(in);
    return is.readObject();
  }
 catch (  Throwable e) {
    throw DataUtils.newIllegalArgumentException("Could not deserialize {0}",Arrays.toString(data),e);
  }
}

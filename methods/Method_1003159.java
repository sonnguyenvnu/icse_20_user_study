/** 
 * Serialize the object to a byte array.
 * @param obj the object to serialize
 * @return the byte array
 */
public static byte[] serialize(Object obj){
  try {
    ByteArrayOutputStream out=new ByteArrayOutputStream();
    ObjectOutputStream os=new ObjectOutputStream(out);
    os.writeObject(obj);
    return out.toByteArray();
  }
 catch (  Throwable e) {
    throw DataUtils.newIllegalArgumentException("Could not serialize {0}",obj,e);
  }
}

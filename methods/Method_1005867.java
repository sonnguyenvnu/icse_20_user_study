/** 
 * ????
 * @param bytes
 * @return
 */
public static Object unserialize(byte[] bytes){
  if (bytes == null) {
    return null;
  }
  ByteArrayInputStream bais=null;
  try {
    bais=new ByteArrayInputStream(bytes);
    ObjectInputStream ois=new ObjectInputStream(bais);
    return ois.readObject();
  }
 catch (  Exception e) {
    e.printStackTrace();
    logger.error("??????",e);
  }
  return null;
}

/** 
 * ?byte[] -->Object
 * @param bytes
 * @return
 */
private static Object unserialize(byte[] bytes){
  ByteArrayInputStream bais=null;
  try {
    bais=new ByteArrayInputStream(bytes);
    ObjectInputStream ois=new ObjectInputStream(bais);
    return ois.readObject();
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
 finally {
    try {
      bais.close();
    }
 catch (    IOException e) {
      logger.error(e.getMessage(),e);
    }
  }
  return null;
}

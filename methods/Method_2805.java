/** 
 * ??????
 * @param path
 * @return
 */
public static Object readObjectFrom(String path){
  ObjectInputStream ois=null;
  try {
    ois=new ObjectInputStream(IOUtil.newInputStream(path));
    Object o=ois.readObject();
    ois.close();
    return o;
  }
 catch (  Exception e) {
    logger.warning("??" + path + "?????????" + e);
  }
  return null;
}

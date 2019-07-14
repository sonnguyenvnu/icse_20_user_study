/** 
 * ????
 * @param path
 * @param content
 * @return
 */
public static boolean saveTxt(String path,String content){
  try {
    FileChannel fc=new FileOutputStream(path).getChannel();
    fc.write(ByteBuffer.wrap(content.getBytes()));
    fc.close();
  }
 catch (  Exception e) {
    logger.throwing("IOUtil","saveTxt",e);
    logger.warning("IOUtil saveTxt ?" + path + "??" + e.toString());
    return false;
  }
  return true;
}

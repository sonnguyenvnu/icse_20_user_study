private static boolean loadBin(String path){
  try {
    ObjectInputStream in=new ObjectInputStream(IOUtil.newInputStream(path));
    CONVERT=(char[])in.readObject();
    in.close();
  }
 catch (  Exception e) {
    logger.warning("??????????????????" + e);
    return false;
  }
  return true;
}

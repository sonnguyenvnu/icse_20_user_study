static boolean loadDat(String path){
  try {
    ObjectInputStream in=new ObjectInputStream(IOUtil.newInputStream(path));
    start=(int[])in.readObject();
    if (CoreDictionary.trie.size() != start.length - 1) {
      in.close();
      return false;
    }
    pair=(int[])in.readObject();
    in.close();
  }
 catch (  Exception e) {
    logger.warning("????????" + path + "????[" + e + "]??????????????……");
    return false;
  }
  return true;
}

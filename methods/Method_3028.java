/** 
 * ??dat???
 * @param path
 * @param valueArray
 * @return
 */
protected boolean saveDat(String path,List<V> valueArray){
  try {
    DataOutputStream out=new DataOutputStream(new BufferedOutputStream(IOUtil.newOutputStream(path)));
    out.writeInt(valueArray.size());
    for (    V item : valueArray) {
      saveValue(item,out);
    }
    trie.save(out);
    out.close();
  }
 catch (  Exception e) {
    logger.warning("????" + TextUtility.exceptionToString(e));
    return false;
  }
  return true;
}

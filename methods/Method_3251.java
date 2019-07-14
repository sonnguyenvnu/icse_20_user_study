boolean saveDat(String path,TreeMap<String,Attribute> map){
  Collection<Attribute> attributeList=map.values();
  try {
    DataOutputStream out=new DataOutputStream(IOUtil.newOutputStream(path + Predefine.BIN_EXT));
    out.writeInt(attributeList.size());
    for (    Attribute attribute : attributeList) {
      out.writeInt(attribute.p.length);
      for (int i=0; i < attribute.p.length; ++i) {
        char[] charArray=attribute.dependencyRelation[i].toCharArray();
        out.writeInt(charArray.length);
        for (        char c : charArray) {
          out.writeChar(c);
        }
        out.writeFloat(attribute.p[i]);
      }
    }
    if (!trie.save(out))     return false;
    out.close();
  }
 catch (  Exception e) {
    logger.warning("????" + e);
    return false;
  }
  return true;
}

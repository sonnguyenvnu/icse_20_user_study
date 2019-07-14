static boolean saveDat(String path,AhoCorasickDoubleArrayTrie<String> trie,Set<Map.Entry<String,String>> entrySet){
  if (trie.size() != entrySet.size()) {
    logger.warning("??????");
    return false;
  }
  try {
    DataOutputStream out=new DataOutputStream(new BufferedOutputStream(IOUtil.newOutputStream(path + Predefine.BIN_EXT)));
    out.writeInt(entrySet.size());
    for (    Map.Entry<String,String> entry : entrySet) {
      char[] charArray=entry.getValue().toCharArray();
      out.writeInt(charArray.length);
      for (      char c : charArray) {
        out.writeChar(c);
      }
    }
    trie.save(out);
    out.close();
  }
 catch (  Exception e) {
    logger.warning("???dat" + path + "??");
    return false;
  }
  return true;
}

public boolean load(InputStream inputStream){
  trie=new DoubleArrayTrie<Long[]>();
  TreeMap<String,Set<Long>> treeMap=new TreeMap<String,Set<Long>>();
  String line=null;
  try {
    BufferedReader bw=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
    while ((line=bw.readLine()) != null) {
      String[] args=line.split(" ");
      List<Synonym> synonymList=Synonym.create(args);
      for (      Synonym synonym : synonymList) {
        Set<Long> idSet=treeMap.get(synonym.realWord);
        if (idSet == null) {
          idSet=new TreeSet<Long>();
          treeMap.put(synonym.realWord,idSet);
        }
        idSet.add(synonym.id);
      }
    }
    bw.close();
    List<String> keyList=new ArrayList<String>(treeMap.size());
    for (    String key : treeMap.keySet()) {
      keyList.add(key);
    }
    List<Long[]> valueList=new ArrayList<Long[]>(treeMap.size());
    for (    Set<Long> idSet : treeMap.values()) {
      valueList.add(idSet.toArray(new Long[0]));
    }
    int resultCode=trie.build(keyList,valueList);
    if (resultCode != 0) {
      logger.warning("??" + inputStream + "??????" + resultCode);
      return false;
    }
  }
 catch (  Exception e) {
    logger.warning("??" + inputStream + "???????" + line + "??" + e);
    return false;
  }
  return true;
}

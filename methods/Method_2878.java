public int build(TreeMap<String,Integer> keyValueMap){
  idToLabelMap=new String[keyValueMap.size()];
  for (  Map.Entry<String,Integer> entry : keyValueMap.entrySet()) {
    idToLabelMap[entry.getValue()]=entry.getKey();
  }
  return trie.build(keyValueMap);
}

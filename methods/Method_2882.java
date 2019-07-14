public boolean load(ByteArray byteArray){
  idToLabelMap=new String[byteArray.nextInt()];
  TreeMap<String,Integer> map=new TreeMap<String,Integer>();
  for (int i=0; i < idToLabelMap.length; i++) {
    idToLabelMap[i]=byteArray.nextString();
    map.put(idToLabelMap[i],i);
  }
  return trie.build(map) == 0;
}

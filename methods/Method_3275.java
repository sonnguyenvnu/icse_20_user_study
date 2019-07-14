public void shrink(int freq,List<TaggerImpl> taggers){
  if (freq <= 1) {
    return;
  }
  int newMaxId=0;
  Map<Integer,Integer> old2new=new TreeMap<Integer,Integer>();
  List<String> deletedKeys=new ArrayList<String>(dic_.size() / 8);
  List<Map.Entry<String,Integer>> l=new LinkedList<Map.Entry<String,Integer>>(dic_.entrySet());
  for (  MutableDoubleArrayTrieInteger.KeyValuePair pair : dic_) {
    String key=pair.key();
    int id=pair.value();
    int cid=continuousId(id);
    int f=frequency.get(cid);
    if (f >= freq) {
      old2new.put(id,newMaxId);
      pair.setValue(newMaxId);
      newMaxId+=(key.charAt(0) == 'U' ? y_.size() : y_.size() * y_.size());
    }
 else {
      deletedKeys.add(key);
    }
  }
  for (  String key : deletedKeys) {
    dic_.remove(key);
  }
  for (  TaggerImpl tagger : taggers) {
    List<List<Integer>> featureCache=tagger.getFeatureCache_();
    for (int k=0; k < featureCache.size(); k++) {
      List<Integer> featureCacheItem=featureCache.get(k);
      List<Integer> newCache=new ArrayList<Integer>();
      for (      Integer it : featureCacheItem) {
        if (it == -1) {
          continue;
        }
        Integer nid=old2new.get(it);
        if (nid != null) {
          newCache.add(nid);
        }
      }
      newCache.add(-1);
      featureCache.set(k,newCache);
    }
  }
  maxid_=newMaxId;
}

/** 
 * @param ratio ???c???????????????1-c?
 * @param threshold ?????????????
 * @return
 */
public LinearModel compress(final double ratio,final double threshold){
  if (ratio < 0 || ratio >= 1) {
    throw new IllegalArgumentException("??????? 0 ? 1 ??");
  }
  if (ratio == 0)   return this;
  Set<Map.Entry<String,Integer>> featureIdSet=featureMap.entrySet();
  TagSet tagSet=featureMap.tagSet;
  MaxHeap<FeatureSortItem> heap=new MaxHeap<FeatureSortItem>((int)((featureIdSet.size() - tagSet.sizeIncludingBos()) * (1.0f - ratio)),new Comparator<FeatureSortItem>(){
    @Override public int compare(    FeatureSortItem o1,    FeatureSortItem o2){
      return Float.compare(o1.total,o2.total);
    }
  }
);
  logger.start("????...\n");
  int logEvery=(int)Math.ceil(featureMap.size() / 10000f);
  int n=0;
  for (  Map.Entry<String,Integer> entry : featureIdSet) {
    if (++n % logEvery == 0 || n == featureMap.size()) {
      logger.out("\r%.2f%% ",MathUtility.percentage(n,featureMap.size()));
    }
    if (entry.getValue() < tagSet.sizeIncludingBos()) {
      continue;
    }
    FeatureSortItem item=new FeatureSortItem(entry,this.parameter,tagSet.size());
    if (item.total < threshold)     continue;
    heap.add(item);
  }
  logger.finish("\n????\n");
  int size=heap.size() + tagSet.sizeIncludingBos();
  float[] parameter=new float[size * tagSet.size()];
  MutableDoubleArrayTrieInteger mdat=new MutableDoubleArrayTrieInteger();
  for (  Map.Entry<String,Integer> tag : tagSet) {
    mdat.add("BL=" + tag.getKey());
  }
  mdat.add("BL=_BL_");
  for (int i=0; i < tagSet.size() * tagSet.sizeIncludingBos(); i++) {
    parameter[i]=this.parameter[i];
  }
  logger.start("?????trie?...\n");
  logEvery=(int)Math.ceil(heap.size() / 10000f);
  n=0;
  for (  FeatureSortItem item : heap) {
    if (++n % logEvery == 0 || n == heap.size()) {
      logger.out("\r%.2f%% ",MathUtility.percentage(n,heap.size()));
    }
    int id=mdat.size();
    mdat.put(item.key,id);
    for (int i=0; i < tagSet.size(); ++i) {
      parameter[id * tagSet.size() + i]=this.parameter[item.id * tagSet.size() + i];
    }
  }
  logger.finish("\n????\n");
  this.featureMap=new ImmutableFeatureMDatMap(mdat,tagSet);
  this.parameter=parameter;
  return this;
}

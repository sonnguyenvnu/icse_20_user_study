@Override public ProviderInfo doSelect(SofaRequest request,List<ProviderInfo> providerInfos){
  String key=getServiceKey(request);
  int length=providerInfos.size();
  int maxWeight=0;
  int minWeight=Integer.MAX_VALUE;
  final LinkedHashMap<ProviderInfo,IntegerWrapper> invokerToWeightMap=new LinkedHashMap<ProviderInfo,IntegerWrapper>();
  int weightSum=0;
  for (  ProviderInfo providerInfo : providerInfos) {
    int weight=getWeight(providerInfo);
    maxWeight=Math.max(maxWeight,weight);
    minWeight=Math.min(minWeight,weight);
    if (weight > 0) {
      invokerToWeightMap.put(providerInfo,new IntegerWrapper(weight));
      weightSum+=weight;
    }
  }
  PositiveAtomicCounter sequence=sequences.get(key);
  if (sequence == null) {
    sequences.putIfAbsent(key,new PositiveAtomicCounter());
    sequence=sequences.get(key);
  }
  int currentSequence=sequence.getAndIncrement();
  if (maxWeight > 0 && minWeight < maxWeight) {
    int mod=currentSequence % weightSum;
    for (int i=0; i < maxWeight; i++) {
      for (      Map.Entry<ProviderInfo,IntegerWrapper> each : invokerToWeightMap.entrySet()) {
        final ProviderInfo k=each.getKey();
        final IntegerWrapper v=each.getValue();
        if (mod == 0 && v.getValue() > 0) {
          return k;
        }
        if (v.getValue() > 0) {
          v.decrement();
          mod--;
        }
      }
    }
  }
  return providerInfos.get(currentSequence % length);
}

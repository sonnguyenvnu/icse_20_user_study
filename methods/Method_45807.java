@Override public ProviderInfo doSelect(SofaRequest invocation,List<ProviderInfo> providerInfos){
  ProviderInfo providerInfo=null;
  int size=providerInfos.size();
  int totalWeight=0;
  boolean isWeightSame=true;
  for (int i=0; i < size; i++) {
    int weight=getWeight(providerInfos.get(i));
    totalWeight+=weight;
    if (isWeightSame && i > 0 && weight != getWeight(providerInfos.get(i - 1))) {
      isWeightSame=false;
    }
  }
  if (totalWeight > 0 && !isWeightSame) {
    int offset=random.nextInt(totalWeight);
    for (int i=0; i < size; i++) {
      offset-=getWeight(providerInfos.get(i));
      if (offset < 0) {
        providerInfo=providerInfos.get(i);
        break;
      }
    }
  }
 else {
    providerInfo=providerInfos.get(random.nextInt(size));
  }
  return providerInfo;
}

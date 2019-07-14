@Override public ProviderInfo doSelect(SofaRequest request,List<ProviderInfo> providerInfos){
  String key=getServiceKey(request);
  int length=providerInfos.size();
  PositiveAtomicCounter sequence=sequences.get(key);
  if (sequence == null) {
    sequences.putIfAbsent(key,new PositiveAtomicCounter());
    sequence=sequences.get(key);
  }
  return providerInfos.get(sequence.getAndIncrement() % length);
}

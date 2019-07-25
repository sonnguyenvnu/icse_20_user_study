BrokerGroupInfo select(BrokerClusterInfo cluster){
  List<BrokerGroupInfo> groups=cluster.getGroups();
  if (groups == null || groups.isEmpty())   return null;
  int size=groups.size();
  int totalWeight=0;
  boolean sameWeight=true;
  int lastWeight=-1;
  for (int i=0; i < size; ++i) {
    BrokerGroupInfo group=groups.get(i);
    if (!group.isAvailable())     continue;
    Integer weight=weights.get(group);
    if (weight == null) {
      weights.putIfAbsent(group,DEFAULT_WEIGHT);
      weight=weights.get(group);
    }
    if (lastWeight != -1 && lastWeight != weight) {
      sameWeight=false;
    }
    lastWeight=weight;
    totalWeight+=weight;
  }
  if (totalWeight == 0)   return null;
  if (totalWeight > 0 && !sameWeight) {
    int offset=random.nextInt(totalWeight);
    for (int i=0; i < size; ++i) {
      BrokerGroupInfo group=groups.get(i);
      if (!group.isAvailable())       continue;
      Integer weight=weights.get(group);
      offset-=weight;
      if (offset <= 0) {
        return group;
      }
    }
  }
  int index=random.nextInt(size);
  return groups.get(index);
}

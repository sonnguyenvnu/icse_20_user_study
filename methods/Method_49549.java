private EntryList findEntriesMatchingQuery(SliceQuery query,EntryList sortedEntries){
  int lowestStartMatch=sortedEntries.size();
  int highestEndMatch=-1;
  final StaticBuffer queryStart=query.getSliceStart();
  final StaticBuffer queryEnd=query.getSliceEnd();
  int low=0;
  int high=sortedEntries.size() - 1;
  while (low <= high) {
    int mid=(low + high) >>> 1;
    Entry midVal=sortedEntries.get(mid);
    int cmpStart=queryStart.compareTo(midVal.getColumn());
    if (0 < cmpStart) {
      if (lowestStartMatch == mid + 1) {
        break;
      }
      low=mid + 1;
    }
 else {
      if (mid < lowestStartMatch) {
        lowestStartMatch=mid;
      }
      high=mid - 1;
    }
  }
  if (sortedEntries.size() == lowestStartMatch) {
    return EntryList.EMPTY_LIST;
  }
  low=0;
  high=sortedEntries.size() - 1;
  while (low <= high) {
    int mid=(low + high) >>> 1;
    Entry midVal=sortedEntries.get(mid);
    int cmpEnd=queryEnd.compareTo(midVal.getColumn());
    if (0 < cmpEnd) {
      if (mid > highestEndMatch) {
        highestEndMatch=mid;
      }
      low=mid + 1;
    }
 else {
      if (highestEndMatch == mid - 1) {
        break;
      }
      high=mid - 1;
    }
  }
  if (0 <= highestEndMatch - lowestStartMatch) {
    int endIndex=highestEndMatch + 1;
    if (query.hasLimit()) {
      endIndex=Math.min(endIndex,query.getLimit() + lowestStartMatch);
    }
    return EntryArrayList.of(sortedEntries.subList(lowestStartMatch,endIndex));
  }
 else {
    return EntryList.EMPTY_LIST;
  }
}

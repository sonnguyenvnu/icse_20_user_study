@Override protected final void buildModels(){
  int numListItemsToUse=isFirstBuildForList ? config().initialLoadSizeHint : config().pageSize;
  if (!list.isEmpty()) {
    isFirstBuildForList=false;
  }
  int numBoundViews=getAdapter().getBoundViewHolders().size();
  if (!hasNotifiedInsufficientPageSize && numBoundViews > numListItemsToUse) {
    onExceptionSwallowed(new IllegalStateException("The page size specified in your PagedList config is smaller than the number of items " + "shown on screen. Increase your page size and/or initial load size."));
    hasNotifiedInsufficientPageSize=true;
  }
  float ratioOfEndItems=scrollingTowardsEnd ? .7f : .3f;
  int itemsToBuildTowardsEnd=(int)(numListItemsToUse * ratioOfEndItems);
  int itemsToBuildTowardsStart=numListItemsToUse - itemsToBuildTowardsEnd;
  int numItemsUntilEnd=list.size() - lastBoundPositionWithinList - 1;
  int leftOverItemsAtEnd=itemsToBuildTowardsEnd - numItemsUntilEnd;
  if (leftOverItemsAtEnd > 0) {
    itemsToBuildTowardsStart+=leftOverItemsAtEnd;
    itemsToBuildTowardsEnd-=leftOverItemsAtEnd;
  }
  int numItemsUntilStart=lastBoundPositionWithinList;
  int leftOverItemsAtStart=itemsToBuildTowardsStart - numItemsUntilStart;
  if (leftOverItemsAtStart > 0) {
    itemsToBuildTowardsStart-=leftOverItemsAtStart;
    itemsToBuildTowardsEnd+=leftOverItemsAtStart;
  }
  lastBuiltLowerBound=Math.max(lastBoundPositionWithinList - itemsToBuildTowardsStart,0);
  lastBuiltUpperBound=Math.min(lastBoundPositionWithinList + itemsToBuildTowardsEnd,list.size());
  buildModels(list.subList(lastBuiltLowerBound,lastBuiltUpperBound));
}

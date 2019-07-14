@SuppressWarnings("unchecked") protected void updateTree(String pattern,int flags){
  delegatingFilterContainers.clear();
  executor.execute(() -> {
    searchInConstantPoolsView.showWaitCursor();
    int matchingTypeCount=0;
    int patternLength=pattern.length();
    if (patternLength > 0) {
      try {
        for (        Future<Indexes> futureIndexes : collectionOfFutureIndexes) {
          if (futureIndexes.isDone()) {
            Indexes indexes=futureIndexes.get();
            HashSet<Container.Entry> matchingEntries=new HashSet<>();
            filter(indexes,pattern,flags,matchingEntries);
            if (!matchingEntries.isEmpty()) {
              Container.Entry parentEntry=matchingEntries.iterator().next();
              Container container=null;
              while (parentEntry.getContainer().getRoot() != null) {
                container=parentEntry.getContainer();
                parentEntry=container.getRoot().getParent();
              }
              matchingEntries=getOuterEntries(matchingEntries);
              matchingTypeCount+=matchingEntries.size();
              delegatingFilterContainers.add(new DelegatingFilterContainer(container,matchingEntries));
            }
          }
        }
      }
 catch (      Exception e) {
        assert ExceptionUtil.printStackTrace(e);
      }
    }
    final int count=matchingTypeCount;
    searchInConstantPoolsView.hideWaitCursor();
    searchInConstantPoolsView.updateTree(delegatingFilterContainers,count);
  }
);
}

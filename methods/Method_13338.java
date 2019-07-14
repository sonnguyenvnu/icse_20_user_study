@SuppressWarnings("unchecked") protected String searchTypeHavingMember(String typeName,String name,String descriptor,Container.Entry entry){
  ArrayList<Container.Entry> entries=new ArrayList<>();
  try {
    for (    Future<Indexes> futureIndexes : collectionOfFutureIndexes) {
      if (futureIndexes.isDone()) {
        Map<String,Collection> index=futureIndexes.get().getIndex("typeDeclarations");
        if (index != null) {
          Collection<Container.Entry> collection=index.get(typeName);
          if (collection != null) {
            entries.addAll(collection);
          }
        }
      }
    }
  }
 catch (  Exception e) {
    assert ExceptionUtil.printStackTrace(e);
  }
  String rootUri=entry.getContainer().getRoot().getUri().toString();
  ArrayList<Container.Entry> sameContainerEntries=new ArrayList<>();
  for (  Container.Entry e : entries) {
    if (e.getUri().toString().startsWith(rootUri)) {
      sameContainerEntries.add(e);
    }
  }
  if (sameContainerEntries.size() > 0) {
    return searchTypeHavingMember(typeName,name,descriptor,sameContainerEntries);
  }
 else {
    return searchTypeHavingMember(typeName,name,descriptor,entries);
  }
}

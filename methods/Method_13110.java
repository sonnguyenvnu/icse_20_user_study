@SuppressWarnings("unchecked") protected List<Container.Entry> getEntries(String typeName){
  ArrayList<Container.Entry> result=new ArrayList<>();
  try {
    for (    Future<Indexes> futureIndexes : collectionOfFutureIndexes) {
      if (futureIndexes.isDone()) {
        Map<String,Collection> typeDeclarations=futureIndexes.get().getIndex("typeDeclarations");
        if (typeDeclarations != null) {
          Collection<Container.Entry> collection=typeDeclarations.get(typeName);
          if (collection != null) {
            for (            Container.Entry e : collection) {
              if (e != null) {
                result.add(e);
              }
            }
          }
        }
      }
    }
  }
 catch (  Exception e) {
    assert ExceptionUtil.printStackTrace(e);
  }
  return result;
}

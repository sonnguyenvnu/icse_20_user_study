@Override public boolean openURI(int x,int y,Collection<Container.Entry> entries,String query,String fragment){
  if (entries != null) {
    if (entries.size() == 1) {
      Container.Entry entry=entries.iterator().next();
      return openURI(UriUtil.createURI(this,getCollectionOfFutureIndexes(),entry,query,fragment));
    }
 else {
      Collection<Future<Indexes>> collectionOfFutureIndexes=getCollectionOfFutureIndexes();
      selectLocationController.show(new Point(x + (16 + 2),y + 2),entries,entry -> openURI(UriUtil.createURI(this,collectionOfFutureIndexes,entry,query,fragment)),() -> {
      }
);
      return true;
    }
  }
  return false;
}

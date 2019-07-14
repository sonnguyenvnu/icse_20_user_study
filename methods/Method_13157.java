@SuppressWarnings("unchecked") protected static void index(API api,Container.Entry entry,Indexes indexes,Map<String,Collection> packageDeclarationIndex){
  for (  Container.Entry e : entry.getChildren()) {
    if (e.isDirectory()) {
      String path=e.getPath();
      if (!path.startsWith("classes/META-INF")) {
        packageDeclarationIndex.get(path.substring(8)).add(e);
      }
      index(api,e,indexes,packageDeclarationIndex);
    }
 else {
      Indexer indexer=api.getIndexer(e);
      if (indexer != null) {
        indexer.index(api,e,indexes);
      }
    }
  }
}

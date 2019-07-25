@NotNull @Override public Map<String,Collection<E>> map(@NotNull final FileContent inputData){
  final HashMap<String,Collection<E>> map=new HashMap<>();
  try {
    final SModelData model=RootNodeNameIndex.doModelParsing(myPlatform,inputData);
    if (model == null) {
      return Collections.emptyMap();
    }
    final SModelReference modelRef=model.getReference();
    getObjectsToIndex(model,object -> {
      String[] keys=getKeys(model,object);
      for (      String key : keys) {
        Collection<E> collection=map.get(key);
        if (collection == null) {
          collection=new ArrayList<>();
          map.put(key,collection);
        }
        updateCollection(modelRef,object,collection);
      }
    }
);
  }
 catch (  Exception e) {
    LOG.error("Error indexing model file " + inputData.getFileName(),e);
  }
  return map;
}

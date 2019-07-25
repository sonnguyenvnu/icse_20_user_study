protected static void index(Map keys,Map indexOptions){
  parent$_collection.ensureIndex(translateMapToDBObject(keys),translateMapToDBObject(indexOptions));
}

private void init(){
  if (kclass != null) {
    collection=(DBCollection)staticMethod(kclass,"collection");
  }
  if (collection == null && !isEmpty(tableName))   collection=Document.mongoMongo.collection(tableName);
}

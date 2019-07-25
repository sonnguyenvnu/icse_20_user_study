public List fetch(){
  processOptions();
  processSelector();
  List result=list();
  Map sort=(Map)options.get("sort");
  Integer skip=(Integer)options.get("skip");
  Integer limit=(Integer)options.get("limit");
  Map fields=(Map)options.get("fields");
  DBCursor dbCursor=fields == null ? collection().find(translateMapToDBObject(selector)) : collection().find(translateMapToDBObject(selector),translateMapToDBObject(fields));
  if (sort != null)   dbCursor.sort(translateMapToDBObject(sort));
  if (skip != null)   dbCursor.skip(skip);
  if (limit != null)   dbCursor.limit(limit);
  try {
    while (dbCursor.hasNext()) {
      DBObject dbObject=dbCursor.next();
      if (kclass == null) {
        result.add(dbObject.toMap());
      }
 else {
        result.add(staticMethod(kclass,"create",dbObject.toMap()));
      }
    }
  }
  finally {
    dbCursor.close();
  }
  return result;
}

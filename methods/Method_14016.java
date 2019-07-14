/** 
 * Group a list of ItemUpdates by subject: this is useful to make one single edit per item.
 * @param itemDocuments
 * @return a map from item ids to merged ItemUpdate for that id
 */
public static Map<EntityIdValue,ItemUpdate> groupBySubject(List<ItemUpdate> itemDocuments){
  Map<EntityIdValue,ItemUpdate> map=new HashMap<>();
  for (  ItemUpdate update : itemDocuments) {
    if (update.isNull()) {
      continue;
    }
    ItemIdValue qid=update.getItemId();
    if (map.containsKey(qid)) {
      ItemUpdate oldUpdate=map.get(qid);
      map.put(qid,oldUpdate.merge(update));
    }
 else {
      map.put(qid,update);
    }
  }
  return map;
}

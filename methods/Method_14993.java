/** 
 * ????
 * @param list
 * @return
 */
public static List<CommentItem> toSingleLevelList(List<CommentItem> list){
  if (list == null || list.isEmpty()) {
    return list;
  }
  Map<Long,CommentItem> parentMap=new LinkedHashMap<>();
  long id;
  long toId;
  for (  CommentItem item : list) {
    id=item == null ? 0 : item.getId();
    if (id <= 0) {
      continue;
    }
    parentMap.put(id,item);
  }
  CommentItem parent;
  for (  final CommentItem item : new ArrayList<>(parentMap.values())) {
    parent=null;
    toId=item.getToId();
    if (toId > 0) {
      parent=parentMap.get(toId);
      if (parent == null) {
        parentMap.remove(item.getId());
        continue;
      }
    }
    if (parent != null) {
      item.setToUser(parent.getUser());
      parentMap.put(item.getId(),item);
    }
  }
  return new ArrayList<>(parentMap.values());
}

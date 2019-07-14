private static boolean checkIntersection(int start,int end,ArrayList<TLRPC.MessageEntity> entities){
  if (entities == null || entities.isEmpty()) {
    return false;
  }
  int count=entities.size();
  for (int a=0; a < count; a++) {
    TLRPC.MessageEntity entity=entities.get(a);
    if (entity.offset > start && entity.offset + entity.length <= end) {
      return true;
    }
  }
  return false;
}

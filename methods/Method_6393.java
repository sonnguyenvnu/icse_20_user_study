private static boolean checkInclusion(int index,ArrayList<TLRPC.MessageEntity> entities){
  if (entities == null || entities.isEmpty()) {
    return false;
  }
  int count=entities.size();
  for (int a=0; a < count; a++) {
    TLRPC.MessageEntity entity=entities.get(a);
    if (entity.offset <= index && entity.offset + entity.length > index) {
      return true;
    }
  }
  return false;
}

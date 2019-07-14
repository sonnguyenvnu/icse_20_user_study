private static void removeOffsetAfter(int start,int countToRemove,ArrayList<TLRPC.MessageEntity> entities){
  int count=entities.size();
  for (int a=0; a < count; a++) {
    TLRPC.MessageEntity entity=entities.get(a);
    if (entity.offset > start) {
      entity.offset-=countToRemove;
    }
  }
}

private TLObject getAnyParticipant(int userId){
  boolean updated=false;
  for (int a=0; a < 3; a++) {
    SparseArray<TLObject> map;
    ArrayList<TLObject> arrayList;
    if (a == 0) {
      map=contactsMap;
    }
 else     if (a == 1) {
      map=botsMap;
    }
 else {
      map=participantsMap;
    }
    TLObject p=map.get(userId);
    if (p != null) {
      return p;
    }
  }
  return null;
}

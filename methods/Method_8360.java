private void removeParticipants(int userId){
  boolean updated=false;
  for (int a=0; a < 3; a++) {
    SparseArray<TLObject> map;
    ArrayList<TLObject> arrayList;
    if (a == 0) {
      map=contactsMap;
      arrayList=contacts;
    }
 else     if (a == 1) {
      map=botsMap;
      arrayList=bots;
    }
 else {
      map=participantsMap;
      arrayList=participants;
    }
    TLObject p=map.get(userId);
    if (p != null) {
      map.remove(userId);
      arrayList.remove(p);
      updated=true;
    }
  }
  if (updated) {
    updateRows();
    listViewAdapter.notifyDataSetChanged();
  }
}

private void diffWithSnapshot(){
  pendingDeleteMap.clear();
  newMap.clear();
  List<L> groups=getGroups();
  for (int i=0, size=groups.size(); i < size; i++) {
    L group=groups.get(i);
    newMap.put(System.identityHashCode(group),group);
  }
  for (int i=0, size=newMap.size(); i < size; i++) {
    int key=newMap.keyAt(i);
    if (oldMap.get(key) != null) {
      oldMap.remove(key);
      pendingDeleteMap.put(key,true);
    }
  }
  for (int i=0, size=pendingDeleteMap.size(); i < size; i++) {
    newMap.remove(pendingDeleteMap.keyAt(i));
  }
  diffGroup(newMap,oldMap);
  oldMap.clear();
  newMap.clear();
}

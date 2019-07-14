public void addToPollsQueue(long dialogId,ArrayList<MessageObject> visibleObjects){
  SparseArray<MessageObject> array=pollsToCheck.get(dialogId);
  if (array == null) {
    array=new SparseArray<>();
    pollsToCheck.put(dialogId,array);
    pollsToCheckSize++;
  }
  for (int a=0, N=array.size(); a < N; a++) {
    MessageObject object=array.valueAt(a);
    object.pollVisibleOnScreen=false;
  }
  for (int a=0, N=visibleObjects.size(); a < N; a++) {
    MessageObject messageObject=visibleObjects.get(a);
    if (messageObject.type != MessageObject.TYPE_POLL) {
      continue;
    }
    int id=messageObject.getId();
    MessageObject object=array.get(id);
    if (object != null) {
      object.pollVisibleOnScreen=true;
    }
 else {
      array.put(id,messageObject);
    }
  }
}

private void addToPolls(MessageObject obj,MessageObject old){
  long pollId=obj.getPollId();
  if (pollId != 0) {
    ArrayList<MessageObject> arrayList=polls.get(pollId);
    if (arrayList == null) {
      arrayList=new ArrayList<>();
      polls.put(pollId,arrayList);
    }
    arrayList.add(obj);
    if (old != null) {
      arrayList.remove(old);
    }
  }
}

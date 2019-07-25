public void clear(String listName){
  MessageList list=getAvailableList(listName,false);
  if (list != null) {
    list.clear();
  }
}

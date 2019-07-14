void addMessage(String message){
  List<Message> list=messageList.getValue();
  if (list == null) {
    list=new ArrayList<>();
  }
  list.add(new Message(message,!emulatingRemoteUser.getValue(),System.currentTimeMillis()));
  clearSuggestions();
  messageList.postValue(list);
}

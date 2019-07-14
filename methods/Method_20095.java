void setMessages(List<Message> messages){
  clearSuggestions();
  messageList.postValue(messages);
}

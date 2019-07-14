private Task<List<SmartReplySuggestion>> generateReplies(List<Message> messages,boolean isEmulatingRemoteUser){
  Message lastMessage=messages.get(messages.size() - 1);
  if (lastMessage.isLocalUser && !isEmulatingRemoteUser || !lastMessage.isLocalUser && isEmulatingRemoteUser) {
    return Tasks.forException(new Exception("Not running smart reply!"));
  }
  List<FirebaseTextMessage> chatHistory=new ArrayList<>();
  for (  Message message : messages) {
    if (message.isLocalUser && !isEmulatingRemoteUser || !message.isLocalUser && isEmulatingRemoteUser) {
      chatHistory.add(FirebaseTextMessage.createForLocalUser(message.text,message.timestamp));
    }
 else {
      chatHistory.add(FirebaseTextMessage.createForRemoteUser(message.text,message.timestamp,REMOTE_USER_ID));
    }
  }
  return FirebaseNaturalLanguage.getInstance().getSmartReply().suggestReplies(chatHistory).continueWith(new Continuation<SmartReplySuggestionResult,List<SmartReplySuggestion>>(){
    @Override public List<SmartReplySuggestion> then(    @NonNull Task<SmartReplySuggestionResult> task){
      return task.getResult().getSuggestions();
    }
  }
);
}

@Override protected void append(@Nullable Project project,@NotNull Priority level,@NotNull String categoryName,@NotNull String messageText,@Nullable Throwable t,@Nullable Object hintObject){
  if (projectMatches(project)) {
    MessageKind kind=MessageKind.fromPriority(level);
    Message message=new Message(kind,categoryName,messageText);
    message.setHintObject(hintObject);
    message.setException(t);
    myMessagesView.add(message);
  }
}

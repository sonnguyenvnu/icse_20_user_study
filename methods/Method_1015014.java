public void forward(GroupInfo targetGroup){
  Conversation conversation=new Conversation(Conversation.ConversationType.Group,targetGroup.target);
  conversation.line=0;
  forward(targetGroup.name,targetGroup.portrait,conversation);
}

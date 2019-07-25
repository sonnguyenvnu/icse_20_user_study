public ChatMessage cover(ChatMessageEntity chatMessageEntity){
  ChatMessage chatMessage=new ChatMessage();
  chatMessage.setReadStatus(chatMessageEntity.getReadStatus());
  chatMessage.setContentType(chatMessageEntity.getContentType());
  chatMessage.setContent(chatMessageEntity.getContent());
  chatMessage.setSendStatus(chatMessageEntity.getSendStatus());
  chatMessage.setCreateTime(chatMessageEntity.getCreatedTime());
  chatMessage.setBelongId(chatMessageEntity.getBelongId());
  chatMessage.setConversationId(chatMessageEntity.getConversationId());
  chatMessage.setMessageType(chatMessageEntity.getMessageType());
  chatMessage.setToId(chatMessageEntity.getToId());
  return chatMessage;
}

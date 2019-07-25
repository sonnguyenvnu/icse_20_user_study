public ChatMessageEntity cover(ChatMessage message){
  ChatMessageEntity chatMessageEntity=new ChatMessageEntity();
  chatMessageEntity.setBelongId(message.getBelongId());
  chatMessageEntity.setContent(message.getContent());
  chatMessageEntity.setContentType(message.getContentType());
  chatMessageEntity.setConversationId(message.getConversationId());
  chatMessageEntity.setCreatedTime(message.getCreateTime());
  chatMessageEntity.setMessageType(message.getMessageType());
  chatMessageEntity.setReadStatus(message.getReadStatus());
  chatMessageEntity.setSendStatus(message.getSendStatus());
  chatMessageEntity.setToId(message.getToId());
  return chatMessageEntity;
}

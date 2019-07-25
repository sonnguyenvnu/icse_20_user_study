public GroupChatEntity cover(GroupChatMessage groupChatMessage){
  GroupChatEntity groupChatEntity=new GroupChatEntity();
  groupChatEntity.setBelongId(groupChatMessage.getBelongId());
  groupChatEntity.setContent(groupChatMessage.getContent());
  groupChatEntity.setCreatedTime(groupChatMessage.getCreateTime());
  groupChatEntity.setGroupId(groupChatMessage.getGroupId());
  groupChatEntity.setContentType(groupChatMessage.getContentType());
  groupChatEntity.setSendStatus(groupChatMessage.getSendStatus());
  groupChatEntity.setReadStatus(groupChatMessage.getReadStatus());
  return groupChatEntity;
}

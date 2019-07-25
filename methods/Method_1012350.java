public GroupChatMessage cover(GroupChatEntity item){
  GroupChatMessage groupChatMessage=new GroupChatMessage();
  groupChatMessage.setContentType(item.getContentType());
  groupChatMessage.setContent(item.getContent());
  groupChatMessage.setGroupId(item.getGroupId());
  groupChatMessage.setBelongId(item.getBelongId());
  groupChatMessage.setCreateTime(item.getCreatedTime());
  groupChatMessage.setReadStatus(item.getReadStatus());
  groupChatMessage.setSendStatus(item.getSendStatus());
  return groupChatMessage;
}

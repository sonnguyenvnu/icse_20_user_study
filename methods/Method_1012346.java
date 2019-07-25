public GroupTableEntity cover(GroupTableMessage groupTableMessage){
  GroupTableEntity groupTableEntity=new GroupTableEntity();
  groupTableEntity.setCreatedTime(groupTableMessage.getCreatedTime());
  groupTableEntity.setCreatorId(groupTableMessage.getCreatorId());
  groupTableEntity.setGroupAvatar(groupTableMessage.getGroupAvatar());
  groupTableEntity.setGroupDescription(groupTableMessage.getGroupDescription());
  groupTableEntity.setGroupId(groupTableMessage.getGroupId());
  groupTableEntity.setGroupName(groupTableMessage.getGroupName());
  groupTableEntity.setGroupNumber(groupTableMessage.getGroupNumber());
  groupTableEntity.setNotification(groupTableMessage.getNotification());
  groupTableEntity.setReadStatus(groupTableMessage.getReadStatus());
  groupTableEntity.setSendStatus(groupTableMessage.getSendStatus());
  groupTableEntity.setToId(groupTableMessage.getToId());
  return groupTableEntity;
}

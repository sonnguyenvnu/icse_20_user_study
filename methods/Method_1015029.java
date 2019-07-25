@OnClick(R.id.actionButton) void action(){
  if (isJoined) {
    Intent intent=ConversationActivity.buildConversationIntent(this,Conversation.ConversationType.Group,groupId,0);
    startActivity(intent);
    finish();
  }
 else {
    groupViewModel.addGroupMember(groupInfo,Collections.singletonList(userId));
  }
}

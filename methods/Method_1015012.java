@ExtContextMenuItem(title="????") public void voip(View containerView,Conversation conversation){
switch (conversation.type) {
case Single:
    videoChat(conversation.target);
  break;
case Group:
pickGroupMemberToVideoChat();
break;
default :
break;
}
}

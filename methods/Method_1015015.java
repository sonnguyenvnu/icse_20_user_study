public void forward(Conversation conversation){
switch (conversation.type) {
case Single:
    UserInfo userInfo=userViewModel.getUserInfo(conversation.target,false);
  forward(userInfo);
break;
case Group:
GroupInfo groupInfo=groupViewModel.getGroupInfo(conversation.target,false);
forward(groupInfo);
break;
default :
break;
}
}

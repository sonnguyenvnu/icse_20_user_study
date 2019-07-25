public void answer(AbstractFrom from,QQMessage m){
  String robotName=getRobotName();
  SmartQQClient client=fContactView.getClient();
  if (from instanceof FriendFrom) {
    if (SmartIMSettings.getInstance().getState().ROBOT_FRIEND_ANY) {
      String reply=getReply(m.getContent(),(QQContact)from.getContact(),null);
      if (reply != null) {
        String input=robotName + reply;
        if (console == null) {
          client.sendMessageToFriend(m.getUserId(),input);
        }
 else {
          console.send(input);
        }
      }
    }
    return;
  }
 else   if (from instanceof GroupFrom) {
    GroupFrom gf=(GroupFrom)from;
    String gName=gf.getGroup().getName();
    if (from.isNewbie()) {
      String welcome=SmartIMSettings.getInstance().getState().ROBOT_GROUP_WELCOME;
      if (welcome != null && !welcome.isEmpty()) {
        GroupInfo info=gf.getGroup();
        GroupUser gu=gf.getGroupUser();
        String input=welcome;
        if (gu != null) {
          input=input.replace("{user}",gu.getName());
        }
        if (info != null) {
          input=input.replace("{memo}",info.getMemo());
        }
        if (console != null) {
          console.send(robotName + input);
        }
 else {
          client.sendMessageToGroup(gf.getGroup().getId(),robotName + input);
        }
      }
    }
    if (atMe(from,m)) {
      String msg=m.getContent(true).trim();
      String reply=getReply(msg,from.getMember(),gName);
      if (reply != null) {
        String input=robotName + "@" + from.getMember().getName() + SEP + reply;
        if (console != null) {
          console.send(input);
        }
 else {
          if (m instanceof GroupMessage) {
            client.sendMessageToGroup(((GroupMessage)m).getGroupId(),input);
          }
 else           if (m instanceof DiscussMessage) {
            client.sendMessageToDiscuss(((DiscussMessage)m).getDiscussId(),input);
          }
        }
      }
      return;
    }
    if (SmartIMSettings.getInstance().getState().ROBOT_GROUP_ANY) {
      if (from.isNewbie() || isMySend(m.getUserId())) {
        return;
      }
      if (console == null) {
        return;
      }
      if (from instanceof FriendFrom) {
        return;
      }
 else       if (from instanceof GroupFrom) {
        if (((GroupFrom)from).getGroupUser().isUnknown()) {
          return;
        }
      }
 else       if (from instanceof DiscussFrom) {
        if (((DiscussFrom)from).getDiscussUser().isUnknown()) {
          return;
        }
      }
      String reply=getReply(m.getContent(),from.getMember(),gName);
      if (reply != null) {
        String input=robotName + "@" + from.getName() + SEP + reply;
        if (console != null) {
          console.send(input);
        }
      }
    }
  }
}

public void answer(AbstractFrom from,WechatMessage m){
  String robotName=getRobotName();
  if (from instanceof UserFrom) {
    if (SmartIMSettings.getInstance().getState().ROBOT_FRIEND_ANY) {
      Contact contact=(Contact)from.getContact();
      if (contact.isSpecial() || contact.is3rdApp()) {
        return;
      }
      String reply=getReply(m.getText().toString(),contact,null);
      if (reply != null) {
        String input=robotName + reply;
        send(from,input);
      }
    }
  }
 else   if (from instanceof GroupFrom) {
    GroupFrom gf=(GroupFrom)from;
    if (from.isNewbie()) {
      String welcome=SmartIMSettings.getInstance().getState().ROBOT_GROUP_WELCOME;
      if (welcome != null && !welcome.isEmpty() && gf != null) {
        String input=welcome;
        if (gf.getMember() != null) {
          input=input.replace("{memo}","");
          input=input.replace("{user}",WXUtils.getPureName(gf.getMember().getName()));
        }
        input=robotName + input;
        send(from,input);
      }
    }
    String name=getMyGroupName(from,m);
    String text=m.getText().toString();
    if (text.contains("@" + name)) {
      text=text.replace("@" + name,"");
      String reply=getReply(text,gf.getMember(),gf.getName());
      if (reply != null) {
        String input=robotName + "@" + from.getMember().getName() + SEP + reply;
        send(from,input);
      }
      return;
    }
    if (SmartIMSettings.getInstance().getState().ROBOT_GROUP_ANY) {
      if (from.isNewbie() || isMySend(m.FromUserName)) {
        return;
      }
      if (console == null) {
        return;
      }
      if (from instanceof UserFrom) {
        return;
      }
 else       if (from instanceof GroupFrom) {
        if (((GroupFrom)from).getMember().isUnknown()) {
          return;
        }
      }
      String reply=getReply(text,gf.getMember(),gf.getName());
      if (reply != null) {
        String input=robotName + "@" + from.getName() + SEP + reply;
        send(null,input);
      }
    }
  }
}

private String getUserName(TLRPC.User user,ArrayList<TLRPC.MessageEntity> entities,int offset){
  String name;
  if (user == null) {
    name="";
  }
 else {
    name=ContactsController.formatName(user.first_name,user.last_name);
  }
  if (offset >= 0) {
    TLRPC.TL_messageEntityMentionName entity=new TLRPC.TL_messageEntityMentionName();
    entity.user_id=user.id;
    entity.offset=offset;
    entity.length=name.length();
    entities.add(entity);
  }
  if (!TextUtils.isEmpty(user.username)) {
    if (offset >= 0) {
      TLRPC.TL_messageEntityMentionName entity=new TLRPC.TL_messageEntityMentionName();
      entity.user_id=user.id;
      entity.offset=offset + name.length() + 2;
      entity.length=user.username.length() + 1;
      entities.add(entity);
    }
    return String.format("%1$s (@%2$s)",name,user.username);
  }
  return name;
}

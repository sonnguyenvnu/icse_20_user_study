public static boolean canUserDoAdminAction(TLRPC.Chat chat,int action){
  if (chat == null) {
    return false;
  }
  if (chat.creator) {
    return true;
  }
  if (chat.admin_rights != null) {
    boolean value;
switch (action) {
case ACTION_PIN:
      value=chat.admin_rights.pin_messages;
    break;
case ACTION_CHANGE_INFO:
  value=chat.admin_rights.change_info;
break;
case ACTION_INVITE:
value=chat.admin_rights.invite_users;
break;
case ACTION_ADD_ADMINS:
value=chat.admin_rights.add_admins;
break;
case ACTION_POST:
value=chat.admin_rights.post_messages;
break;
case ACTION_EDIT_MESSAGES:
value=chat.admin_rights.edit_messages;
break;
case ACTION_DELETE_MESSAGES:
value=chat.admin_rights.delete_messages;
break;
case ACTION_BLOCK_USERS:
value=chat.admin_rights.ban_users;
break;
default :
value=false;
break;
}
if (value) {
return true;
}
}
return false;
}

public static void showAddUserAlert(String error,final BaseFragment fragment,boolean isChannel){
  if (error == null || fragment == null || fragment.getParentActivity() == null) {
    return;
  }
  AlertDialog.Builder builder=new AlertDialog.Builder(fragment.getParentActivity());
  builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
switch (error) {
case "PEER_FLOOD":
    builder.setMessage(LocaleController.getString("NobodyLikesSpam2",R.string.NobodyLikesSpam2));
  builder.setNegativeButton(LocaleController.getString("MoreInfo",R.string.MoreInfo),(dialogInterface,i) -> MessagesController.getInstance(fragment.getCurrentAccount()).openByUserName("spambot",fragment,1));
break;
case "USER_BLOCKED":
case "USER_BOT":
case "USER_ID_INVALID":
if (isChannel) {
builder.setMessage(LocaleController.getString("ChannelUserCantAdd",R.string.ChannelUserCantAdd));
}
 else {
builder.setMessage(LocaleController.getString("GroupUserCantAdd",R.string.GroupUserCantAdd));
}
break;
case "USERS_TOO_MUCH":
if (isChannel) {
builder.setMessage(LocaleController.getString("ChannelUserAddLimit",R.string.ChannelUserAddLimit));
}
 else {
builder.setMessage(LocaleController.getString("GroupUserAddLimit",R.string.GroupUserAddLimit));
}
break;
case "USER_NOT_MUTUAL_CONTACT":
if (isChannel) {
builder.setMessage(LocaleController.getString("ChannelUserLeftError",R.string.ChannelUserLeftError));
}
 else {
builder.setMessage(LocaleController.getString("GroupUserLeftError",R.string.GroupUserLeftError));
}
break;
case "ADMINS_TOO_MUCH":
if (isChannel) {
builder.setMessage(LocaleController.getString("ChannelUserCantAdmin",R.string.ChannelUserCantAdmin));
}
 else {
builder.setMessage(LocaleController.getString("GroupUserCantAdmin",R.string.GroupUserCantAdmin));
}
break;
case "BOTS_TOO_MUCH":
if (isChannel) {
builder.setMessage(LocaleController.getString("ChannelUserCantBot",R.string.ChannelUserCantBot));
}
 else {
builder.setMessage(LocaleController.getString("GroupUserCantBot",R.string.GroupUserCantBot));
}
break;
case "USER_PRIVACY_RESTRICTED":
if (isChannel) {
builder.setMessage(LocaleController.getString("InviteToChannelError",R.string.InviteToChannelError));
}
 else {
builder.setMessage(LocaleController.getString("InviteToGroupError",R.string.InviteToGroupError));
}
break;
case "USERS_TOO_FEW":
builder.setMessage(LocaleController.getString("CreateGroupError",R.string.CreateGroupError));
break;
case "USER_RESTRICTED":
builder.setMessage(LocaleController.getString("UserRestricted",R.string.UserRestricted));
break;
case "YOU_BLOCKED_USER":
builder.setMessage(LocaleController.getString("YouBlockedUser",R.string.YouBlockedUser));
break;
case "CHAT_ADMIN_BAN_REQUIRED":
case "USER_KICKED":
builder.setMessage(LocaleController.getString("AddAdminErrorBlacklisted",R.string.AddAdminErrorBlacklisted));
break;
case "CHAT_ADMIN_INVITE_REQUIRED":
builder.setMessage(LocaleController.getString("AddAdminErrorNotAMember",R.string.AddAdminErrorNotAMember));
break;
case "USER_ADMIN_INVALID":
builder.setMessage(LocaleController.getString("AddBannedErrorAdmin",R.string.AddBannedErrorAdmin));
break;
default :
builder.setMessage(LocaleController.getString("ErrorOccurred",R.string.ErrorOccurred) + "\n" + error);
break;
}
builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
fragment.showDialog(builder.create(),true,null);
}

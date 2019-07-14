private static void performAskAQuestion(BaseFragment fragment){
  int currentAccount=fragment.getCurrentAccount();
  final SharedPreferences preferences=MessagesController.getMainSettings(currentAccount);
  int uid=preferences.getInt("support_id",0);
  TLRPC.User supportUser=null;
  if (uid != 0) {
    supportUser=MessagesController.getInstance(currentAccount).getUser(uid);
    if (supportUser == null) {
      String userString=preferences.getString("support_user",null);
      if (userString != null) {
        try {
          byte[] datacentersBytes=Base64.decode(userString,Base64.DEFAULT);
          if (datacentersBytes != null) {
            SerializedData data=new SerializedData(datacentersBytes);
            supportUser=TLRPC.User.TLdeserialize(data,data.readInt32(false),false);
            if (supportUser != null && supportUser.id == 333000) {
              supportUser=null;
            }
            data.cleanup();
          }
        }
 catch (        Exception e) {
          FileLog.e(e);
          supportUser=null;
        }
      }
    }
  }
  if (supportUser == null) {
    final AlertDialog progressDialog=new AlertDialog(fragment.getParentActivity(),3);
    progressDialog.setCanCacnel(false);
    progressDialog.show();
    TLRPC.TL_help_getSupport req=new TLRPC.TL_help_getSupport();
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
      if (error == null) {
        final TLRPC.TL_help_support res=(TLRPC.TL_help_support)response;
        AndroidUtilities.runOnUIThread(() -> {
          SharedPreferences.Editor editor=preferences.edit();
          editor.putInt("support_id",res.user.id);
          SerializedData data=new SerializedData();
          res.user.serializeToStream(data);
          editor.putString("support_user",Base64.encodeToString(data.toByteArray(),Base64.DEFAULT));
          editor.commit();
          data.cleanup();
          try {
            progressDialog.dismiss();
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
          ArrayList<TLRPC.User> users=new ArrayList<>();
          users.add(res.user);
          MessagesStorage.getInstance(currentAccount).putUsersAndChats(users,null,true,true);
          MessagesController.getInstance(currentAccount).putUser(res.user,false);
          Bundle args=new Bundle();
          args.putInt("user_id",res.user.id);
          fragment.presentFragment(new ChatActivity(args));
        }
);
      }
 else {
        AndroidUtilities.runOnUIThread(() -> {
          try {
            progressDialog.dismiss();
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
        }
);
      }
    }
);
  }
 else {
    MessagesController.getInstance(currentAccount).putUser(supportUser,true);
    Bundle args=new Bundle();
    args.putInt("user_id",supportUser.id);
    fragment.presentFragment(new ChatActivity(args));
  }
}

public void reloadContactsStatuses(){
  saveContactsLoadTime();
  MessagesController.getInstance(currentAccount).clearFullUsers();
  SharedPreferences preferences=MessagesController.getMainSettings(currentAccount);
  final SharedPreferences.Editor editor=preferences.edit();
  editor.putBoolean("needGetStatuses",true).commit();
  TLRPC.TL_contacts_getStatuses req=new TLRPC.TL_contacts_getStatuses();
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (error == null) {
      AndroidUtilities.runOnUIThread(() -> {
        editor.remove("needGetStatuses").commit();
        TLRPC.Vector vector=(TLRPC.Vector)response;
        if (!vector.objects.isEmpty()) {
          ArrayList<TLRPC.User> dbUsersStatus=new ArrayList<>();
          for (          Object object : vector.objects) {
            TLRPC.User toDbUser=new TLRPC.TL_user();
            TLRPC.TL_contactStatus status=(TLRPC.TL_contactStatus)object;
            if (status == null) {
              continue;
            }
            if (status.status instanceof TLRPC.TL_userStatusRecently) {
              status.status.expires=-100;
            }
 else             if (status.status instanceof TLRPC.TL_userStatusLastWeek) {
              status.status.expires=-101;
            }
 else             if (status.status instanceof TLRPC.TL_userStatusLastMonth) {
              status.status.expires=-102;
            }
            TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(status.user_id);
            if (user != null) {
              user.status=status.status;
            }
            toDbUser.status=status.status;
            dbUsersStatus.add(toDbUser);
          }
          MessagesStorage.getInstance(currentAccount).updateUsers(dbUsersStatus,true,true,true);
        }
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.updateInterfaces,MessagesController.UPDATE_MASK_STATUS);
      }
);
    }
  }
);
}

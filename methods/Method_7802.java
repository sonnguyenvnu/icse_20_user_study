private void processSearch(final String query){
  AndroidUtilities.runOnUIThread(() -> {
    if (allowUsernameSearch) {
      searchAdapterHelper.queryServerSearch(query,true,allowChats,allowBots,true,channelId,-1);
    }
    final int currentAccount=UserConfig.selectedAccount;
    final ArrayList<TLRPC.TL_contact> contactsCopy=new ArrayList<>(ContactsController.getInstance(currentAccount).contacts);
    Utilities.searchQueue.postRunnable(() -> {
      String search1=query.trim().toLowerCase();
      if (search1.length() == 0) {
        updateSearchResults(new ArrayList<>(),new ArrayList<>());
        return;
      }
      String search2=LocaleController.getInstance().getTranslitString(search1);
      if (search1.equals(search2) || search2.length() == 0) {
        search2=null;
      }
      String[] search=new String[1 + (search2 != null ? 1 : 0)];
      search[0]=search1;
      if (search2 != null) {
        search[1]=search2;
      }
      ArrayList<TLObject> resultArray=new ArrayList<>();
      ArrayList<CharSequence> resultArrayNames=new ArrayList<>();
      for (int a=0; a < contactsCopy.size(); a++) {
        TLRPC.TL_contact contact=contactsCopy.get(a);
        TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(contact.user_id);
        if (user.id == UserConfig.getInstance(currentAccount).getClientUserId() || onlyMutual && !user.mutual_contact || ignoreUsers != null && ignoreUsers.indexOfKey(contact.user_id) >= 0) {
          continue;
        }
        String name=ContactsController.formatName(user.first_name,user.last_name).toLowerCase();
        String tName=LocaleController.getInstance().getTranslitString(name);
        if (name.equals(tName)) {
          tName=null;
        }
        int found=0;
        for (        String q : search) {
          if (name.startsWith(q) || name.contains(" " + q) || tName != null && (tName.startsWith(q) || tName.contains(" " + q))) {
            found=1;
          }
 else           if (user.username != null && user.username.startsWith(q)) {
            found=2;
          }
          if (found != 0) {
            if (found == 1) {
              resultArrayNames.add(AndroidUtilities.generateSearchName(user.first_name,user.last_name,q));
            }
 else {
              resultArrayNames.add(AndroidUtilities.generateSearchName("@" + user.username,null,"@" + q));
            }
            resultArray.add(user);
            break;
          }
        }
      }
      updateSearchResults(resultArray,resultArrayNames);
    }
);
  }
);
}

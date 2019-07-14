public void setSortType(int value){
  sortType=value;
  if (sortType == 2) {
    if (onlineContacts == null) {
      onlineContacts=new ArrayList<>();
      int selfId=UserConfig.getInstance(currentAccount).clientUserId;
      onlineContacts.addAll(ContactsController.getInstance(currentAccount).contacts);
      for (int a=0, N=onlineContacts.size(); a < N; a++) {
        if (onlineContacts.get(a).user_id == selfId) {
          onlineContacts.remove(a);
          break;
        }
      }
    }
    sortOnlineContacts();
  }
 else {
    notifyDataSetChanged();
  }
}

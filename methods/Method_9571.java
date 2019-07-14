private void updateRows(){
  rowCount=0;
  if (!blockedUsersActivity || !getMessagesController().loadingBlockedUsers) {
    blockUserRow=rowCount++;
    blockUserDetailRow=rowCount++;
    int count;
    if (blockedUsersActivity) {
      count=getMessagesController().blockedUsers.size();
    }
 else {
      count=uidArray.size();
    }
    if (count != 0) {
      usersHeaderRow=rowCount++;
      usersStartRow=rowCount;
      rowCount+=count;
      usersEndRow=rowCount;
      usersDetailRow=rowCount++;
    }
 else {
      usersHeaderRow=-1;
      usersStartRow=-1;
      usersEndRow=-1;
      usersDetailRow=-1;
    }
  }
  if (listViewAdapter != null) {
    listViewAdapter.notifyDataSetChanged();
  }
}

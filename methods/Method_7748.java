public TLObject getItem(int i){
  if (showContacts) {
    i-=3;
    if (i < 0 || i >= ContactsController.getInstance(currentAccount).contacts.size()) {
      return null;
    }
    return MessagesController.getInstance(currentAccount).getUser(ContactsController.getInstance(currentAccount).contacts.get(i).user_id);
  }
  if (showArchiveHint) {
    i-=2;
  }
  ArrayList<TLRPC.Dialog> arrayList=DialogsActivity.getDialogsArray(currentAccount,dialogsType,folderId,dialogsListFrozen);
  if (hasHints) {
    int count=MessagesController.getInstance(currentAccount).hintDialogs.size();
    if (i < 2 + count) {
      return MessagesController.getInstance(currentAccount).hintDialogs.get(i - 1);
    }
 else {
      i-=count + 2;
    }
  }
  if (i < 0 || i >= arrayList.size()) {
    return null;
  }
  return arrayList.get(i);
}

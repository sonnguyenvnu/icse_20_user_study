private void checkVisibleRows(){
  int count=listView.getChildCount();
  for (int a=0; a < count; a++) {
    View child=listView.getChildAt(a);
    if (child instanceof GroupCreateUserCell) {
      GroupCreateUserCell cell=(GroupCreateUserCell)child;
      TLObject object=cell.getObject();
      int id;
      if (object instanceof TLRPC.User) {
        id=((TLRPC.User)object).id;
      }
 else       if (object instanceof TLRPC.Chat) {
        id=-((TLRPC.Chat)object).id;
      }
 else {
        id=0;
      }
      if (id != 0) {
        if (ignoreUsers != null && ignoreUsers.indexOfKey(id) >= 0) {
          cell.setChecked(true,false);
          cell.setCheckBoxEnabled(false);
        }
 else {
          cell.setChecked(selectedContacts.indexOfKey(id) >= 0,true);
          cell.setCheckBoxEnabled(true);
        }
      }
    }
  }
}

private void checkVisibleRows(){
  int count=listView.getChildCount();
  for (int a=0; a < count; a++) {
    View child=listView.getChildAt(a);
    if (child instanceof InviteUserCell) {
      InviteUserCell cell=(InviteUserCell)child;
      ContactsController.Contact contact=cell.getContact();
      if (contact != null) {
        cell.setChecked(selectedContacts.containsKey(contact.key),true);
      }
    }
  }
}

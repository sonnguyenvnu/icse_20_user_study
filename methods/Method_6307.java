private int getContactsHash(ArrayList<TLRPC.TL_contact> contacts){
  long acc=0;
  contacts=new ArrayList<>(contacts);
  Collections.sort(contacts,(tl_contact,tl_contact2) -> {
    if (tl_contact.user_id > tl_contact2.user_id) {
      return 1;
    }
 else     if (tl_contact.user_id < tl_contact2.user_id) {
      return -1;
    }
    return 0;
  }
);
  int count=contacts.size();
  for (int a=-1; a < count; a++) {
    if (a == -1) {
      acc=((acc * 20261) + 0x80000000L + UserConfig.getInstance(currentAccount).contactsSavedCount) % 0x80000000L;
    }
 else {
      TLRPC.TL_contact set=contacts.get(a);
      acc=((acc * 20261) + 0x80000000L + set.user_id) % 0x80000000L;
    }
  }
  return (int)acc;
}

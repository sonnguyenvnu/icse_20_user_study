private void performWriteContactsToPhoneBook(){
  final ArrayList<TLRPC.TL_contact> contactsArray=new ArrayList<>(contacts);
  Utilities.phoneBookQueue.postRunnable(() -> performWriteContactsToPhoneBookInternal(contactsArray));
}

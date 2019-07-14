@Override public void didSelectContact(final TLRPC.User user,String param,ContactsActivity activity){
  if (user == null) {
    return;
  }
  getMessagesController().blockUser(user.id);
}

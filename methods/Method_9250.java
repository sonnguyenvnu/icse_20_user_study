private void onAddToGroupDone(int count){
  ArrayList<TLRPC.User> result=new ArrayList<>();
  for (int a=0; a < selectedContacts.size(); a++) {
    TLRPC.User user=getMessagesController().getUser(selectedContacts.keyAt(a));
    result.add(user);
  }
  if (delegate2 != null) {
    delegate2.didSelectUsers(result,count);
  }
  finishFragment();
}

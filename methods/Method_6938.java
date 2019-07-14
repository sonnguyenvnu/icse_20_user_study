private boolean updatePrintingUsersWithNewMessages(long uid,ArrayList<MessageObject> messages){
  if (uid > 0) {
    ArrayList<PrintingUser> arr=printingUsers.get(uid);
    if (arr != null) {
      printingUsers.remove(uid);
      return true;
    }
  }
 else   if (uid < 0) {
    ArrayList<Integer> messagesUsers=new ArrayList<>();
    for (    MessageObject message : messages) {
      if (!messagesUsers.contains(message.messageOwner.from_id)) {
        messagesUsers.add(message.messageOwner.from_id);
      }
    }
    ArrayList<PrintingUser> arr=printingUsers.get(uid);
    boolean changed=false;
    if (arr != null) {
      for (int a=0; a < arr.size(); a++) {
        PrintingUser user=arr.get(a);
        if (messagesUsers.contains(user.userId)) {
          arr.remove(a);
          a--;
          if (arr.isEmpty()) {
            printingUsers.remove(uid);
          }
          changed=true;
        }
      }
    }
    if (changed) {
      return true;
    }
  }
  return false;
}

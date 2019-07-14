public void markChannelDialogMessageAsDeleted(ArrayList<Integer> messages,final int channelId){
  MessageObject obj=dialogMessage.get((long)-channelId);
  if (obj != null) {
    for (int a=0; a < messages.size(); a++) {
      Integer id=messages.get(a);
      if (obj.getId() == id) {
        obj.deleted=true;
        break;
      }
    }
  }
}

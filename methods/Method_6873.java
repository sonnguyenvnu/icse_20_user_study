public void reloadWebPages(final long dialog_id,HashMap<String,ArrayList<MessageObject>> webpagesToReload){
  for (  HashMap.Entry<String,ArrayList<MessageObject>> entry : webpagesToReload.entrySet()) {
    final String url=entry.getKey();
    final ArrayList<MessageObject> messages=entry.getValue();
    ArrayList<MessageObject> arrayList=reloadingWebpages.get(url);
    if (arrayList == null) {
      arrayList=new ArrayList<>();
      reloadingWebpages.put(url,arrayList);
    }
    arrayList.addAll(messages);
    TLRPC.TL_messages_getWebPagePreview req=new TLRPC.TL_messages_getWebPagePreview();
    req.message=url;
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
      ArrayList<MessageObject> arrayList1=reloadingWebpages.remove(url);
      if (arrayList1 == null) {
        return;
      }
      TLRPC.TL_messages_messages messagesRes=new TLRPC.TL_messages_messages();
      if (!(response instanceof TLRPC.TL_messageMediaWebPage)) {
        for (int a=0; a < arrayList1.size(); a++) {
          arrayList1.get(a).messageOwner.media.webpage=new TLRPC.TL_webPageEmpty();
          messagesRes.messages.add(arrayList1.get(a).messageOwner);
        }
      }
 else {
        TLRPC.TL_messageMediaWebPage media=(TLRPC.TL_messageMediaWebPage)response;
        if (media.webpage instanceof TLRPC.TL_webPage || media.webpage instanceof TLRPC.TL_webPageEmpty) {
          for (int a=0; a < arrayList1.size(); a++) {
            arrayList1.get(a).messageOwner.media.webpage=media.webpage;
            if (a == 0) {
              ImageLoader.saveMessageThumbs(arrayList1.get(a).messageOwner);
            }
            messagesRes.messages.add(arrayList1.get(a).messageOwner);
          }
        }
 else {
          reloadingWebpagesPending.put(media.webpage.id,arrayList1);
        }
      }
      if (!messagesRes.messages.isEmpty()) {
        MessagesStorage.getInstance(currentAccount).putMessages(messagesRes,dialog_id,-2,0,false);
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.replaceMessagesObjects,dialog_id,arrayList1);
      }
    }
));
  }
}

public void removeRecentGif(final TLRPC.Document document){
  recentGifs.remove(document);
  TLRPC.TL_messages_saveGif req=new TLRPC.TL_messages_saveGif();
  req.id=new TLRPC.TL_inputDocument();
  req.id.id=document.id;
  req.id.access_hash=document.access_hash;
  req.id.file_reference=document.file_reference;
  if (req.id.file_reference == null) {
    req.id.file_reference=new byte[0];
  }
  req.unsave=true;
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (error != null && FileRefController.isFileRefError(error.text)) {
      FileRefController.getInstance(currentAccount).requestReference("gif",req);
    }
  }
);
  MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
    try {
      MessagesStorage.getInstance(currentAccount).getDatabase().executeFast("DELETE FROM web_recent_v3 WHERE id = '" + document.id + "' AND type = 2").stepThis().dispose();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}

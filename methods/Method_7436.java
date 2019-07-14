@Override public void onChannelOpened(final Channel ch){
  GoogleApiClient apiClient=new GoogleApiClient.Builder(WearDataLayerListenerService.this).addApi(Wearable.API).build();
  if (!apiClient.blockingConnect().isSuccess()) {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.e("failed to connect google api client");
    }
    return;
  }
  String path=ch.getPath();
  if (BuildVars.LOGS_ENABLED) {
    FileLog.d("wear channel path: " + path);
  }
  try {
    if ("/getCurrentUser".equals(path)) {
      DataOutputStream out=new DataOutputStream(new BufferedOutputStream(ch.getOutputStream(apiClient).await().getOutputStream()));
      if (UserConfig.getInstance(currentAccount).isClientActivated()) {
        final TLRPC.User user=UserConfig.getInstance(currentAccount).getCurrentUser();
        out.writeInt(user.id);
        out.writeUTF(user.first_name);
        out.writeUTF(user.last_name);
        out.writeUTF(user.phone);
        if (user.photo != null) {
          final File photo=FileLoader.getPathToAttach(user.photo.photo_small,true);
          final CyclicBarrier barrier=new CyclicBarrier(2);
          if (!photo.exists()) {
            final NotificationCenter.NotificationCenterDelegate listener=new NotificationCenter.NotificationCenterDelegate(){
              @Override public void didReceivedNotification(              int id,              int account,              Object... args){
                if (id == NotificationCenter.fileDidLoad) {
                  if (BuildVars.LOGS_ENABLED) {
                    FileLog.d("file loaded: " + args[0] + " " + args[0].getClass().getName());
                  }
                  if (args[0].equals(photo.getName())) {
                    if (BuildVars.LOGS_ENABLED) {
                      FileLog.e("LOADED USER PHOTO");
                    }
                    try {
                      barrier.await(10,TimeUnit.MILLISECONDS);
                    }
 catch (                    Exception ignore) {
                    }
                  }
                }
              }
            }
;
            AndroidUtilities.runOnUIThread(new Runnable(){
              @Override public void run(){
                NotificationCenter.getInstance(currentAccount).addObserver(listener,NotificationCenter.fileDidLoad);
                FileLoader.getInstance(currentAccount).loadFile(ImageLocation.getForUser(user,false),user,null,1,1);
              }
            }
);
            try {
              barrier.await(10,TimeUnit.SECONDS);
            }
 catch (            Exception ignore) {
            }
            AndroidUtilities.runOnUIThread(new Runnable(){
              @Override public void run(){
                NotificationCenter.getInstance(currentAccount).removeObserver(listener,NotificationCenter.fileDidLoad);
              }
            }
);
          }
          if (photo.exists() && photo.length() <= 50 * 1024 * 1024) {
            byte[] photoData=new byte[(int)photo.length()];
            FileInputStream photoIn=new FileInputStream(photo);
            new DataInputStream(photoIn).readFully(photoData);
            photoIn.close();
            out.writeInt(photoData.length);
            out.write(photoData);
          }
 else {
            out.writeInt(0);
          }
        }
 else {
          out.writeInt(0);
        }
      }
 else {
        out.writeInt(0);
      }
      out.flush();
      out.close();
    }
 else     if ("/waitForAuthCode".equals(path)) {
      ConnectionsManager.getInstance(currentAccount).setAppPaused(false,false);
      final String[] code={null};
      final CyclicBarrier barrier=new CyclicBarrier(2);
      final NotificationCenter.NotificationCenterDelegate listener=new NotificationCenter.NotificationCenterDelegate(){
        @Override public void didReceivedNotification(        int id,        int account,        Object... args){
          if (id == NotificationCenter.didReceiveNewMessages) {
            long did=(Long)args[0];
            if (did == 777000) {
              ArrayList<MessageObject> arr=(ArrayList<MessageObject>)args[1];
              if (arr.size() > 0) {
                MessageObject msg=arr.get(0);
                if (!TextUtils.isEmpty(msg.messageText)) {
                  Matcher matcher=Pattern.compile("[0-9]+").matcher(msg.messageText);
                  if (matcher.find()) {
                    code[0]=matcher.group();
                    try {
                      barrier.await(10,TimeUnit.MILLISECONDS);
                    }
 catch (                    Exception ignore) {
                    }
                  }
                }
              }
            }
          }
        }
      }
;
      AndroidUtilities.runOnUIThread(new Runnable(){
        @Override public void run(){
          NotificationCenter.getInstance(currentAccount).addObserver(listener,NotificationCenter.didReceiveNewMessages);
        }
      }
);
      try {
        barrier.await(30,TimeUnit.SECONDS);
      }
 catch (      Exception ignore) {
      }
      AndroidUtilities.runOnUIThread(new Runnable(){
        @Override public void run(){
          NotificationCenter.getInstance(currentAccount).removeObserver(listener,NotificationCenter.didReceiveNewMessages);
        }
      }
);
      DataOutputStream out=new DataOutputStream(ch.getOutputStream(apiClient).await().getOutputStream());
      if (code[0] != null)       out.writeUTF(code[0]);
 else       out.writeUTF("");
      out.flush();
      out.close();
      ConnectionsManager.getInstance(currentAccount).setAppPaused(true,false);
    }
 else     if ("/getChatPhoto".equals(path)) {
      DataInputStream in=new DataInputStream(ch.getInputStream(apiClient).await().getInputStream());
      DataOutputStream out=new DataOutputStream(ch.getOutputStream(apiClient).await().getOutputStream());
      try {
        String _req=in.readUTF();
        JSONObject req=new JSONObject(_req);
        int chatID=req.getInt("chat_id");
        int accountID=req.getInt("account_id");
        int currentAccount=-1;
        for (int i=0; i < UserConfig.getActivatedAccountsCount(); i++) {
          if (UserConfig.getInstance(i).getClientUserId() == accountID) {
            currentAccount=i;
            break;
          }
        }
        if (currentAccount != -1) {
          TLRPC.FileLocation location=null;
          if (chatID > 0) {
            TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(chatID);
            if (user != null && user.photo != null)             location=user.photo.photo_small;
          }
 else {
            TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(-chatID);
            if (chat != null && chat.photo != null)             location=chat.photo.photo_small;
          }
          if (location != null) {
            File file=FileLoader.getPathToAttach(location,true);
            if (file.exists() && file.length() < 102400) {
              out.writeInt((int)file.length());
              FileInputStream fin=new FileInputStream(file);
              byte[] buf=new byte[10240];
              int read;
              while ((read=fin.read(buf)) > 0) {
                out.write(buf,0,read);
              }
              fin.close();
            }
 else {
              out.writeInt(0);
            }
          }
 else {
            out.writeInt(0);
          }
        }
 else {
          out.writeInt(0);
        }
        out.flush();
      }
 catch (      Exception ignore) {
      }
 finally {
        in.close();
        out.close();
      }
    }
  }
 catch (  Exception x) {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.e("error processing wear request",x);
    }
  }
  ch.close(apiClient).await();
  apiClient.disconnect();
  if (BuildVars.LOGS_ENABLED) {
    FileLog.d("WearableDataLayer channel thread exiting");
  }
}

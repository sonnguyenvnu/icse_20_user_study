public void updateTimerProc(){
  long currentTime=System.currentTimeMillis();
  checkDeletingTask(false);
  checkReadTasks();
  if (UserConfig.getInstance(currentAccount).isClientActivated()) {
    if (ConnectionsManager.getInstance(currentAccount).getPauseTime() == 0 && ApplicationLoader.isScreenOn && !ApplicationLoader.mainInterfacePausedStageQueue) {
      if (ApplicationLoader.mainInterfacePausedStageQueueTime != 0 && Math.abs(ApplicationLoader.mainInterfacePausedStageQueueTime - System.currentTimeMillis()) > 1000) {
        if (statusSettingState != 1 && (lastStatusUpdateTime == 0 || Math.abs(System.currentTimeMillis() - lastStatusUpdateTime) >= 55000 || offlineSent)) {
          statusSettingState=1;
          if (statusRequest != 0) {
            ConnectionsManager.getInstance(currentAccount).cancelRequest(statusRequest,true);
          }
          TLRPC.TL_account_updateStatus req=new TLRPC.TL_account_updateStatus();
          req.offline=false;
          statusRequest=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
            if (error == null) {
              lastStatusUpdateTime=System.currentTimeMillis();
              offlineSent=false;
              statusSettingState=0;
            }
 else {
              if (lastStatusUpdateTime != 0) {
                lastStatusUpdateTime+=5000;
              }
            }
            statusRequest=0;
          }
);
        }
      }
    }
 else     if (statusSettingState != 2 && !offlineSent && Math.abs(System.currentTimeMillis() - ConnectionsManager.getInstance(currentAccount).getPauseTime()) >= 2000) {
      statusSettingState=2;
      if (statusRequest != 0) {
        ConnectionsManager.getInstance(currentAccount).cancelRequest(statusRequest,true);
      }
      TLRPC.TL_account_updateStatus req=new TLRPC.TL_account_updateStatus();
      req.offline=true;
      statusRequest=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
        if (error == null) {
          offlineSent=true;
        }
 else {
          if (lastStatusUpdateTime != 0) {
            lastStatusUpdateTime+=5000;
          }
        }
        statusRequest=0;
      }
);
    }
    if (updatesQueueChannels.size() != 0) {
      for (int a=0; a < updatesQueueChannels.size(); a++) {
        int key=updatesQueueChannels.keyAt(a);
        long updatesStartWaitTime=updatesStartWaitTimeChannels.valueAt(a);
        if (updatesStartWaitTime + 1500 < currentTime) {
          if (BuildVars.LOGS_ENABLED) {
            FileLog.d("QUEUE CHANNEL " + key + " UPDATES WAIT TIMEOUT - CHECK QUEUE");
          }
          processChannelsUpdatesQueue(key,0);
        }
      }
    }
    for (int a=0; a < 3; a++) {
      if (getUpdatesStartTime(a) != 0 && getUpdatesStartTime(a) + 1500 < currentTime) {
        if (BuildVars.LOGS_ENABLED) {
          FileLog.d(a + " QUEUE UPDATES WAIT TIMEOUT - CHECK QUEUE");
        }
        processUpdatesQueue(a,0);
      }
    }
  }
  if (Math.abs(System.currentTimeMillis() - lastViewsCheckTime) >= 5000) {
    lastViewsCheckTime=System.currentTimeMillis();
    if (channelViewsToSend.size() != 0) {
      for (int a=0; a < channelViewsToSend.size(); a++) {
        final int key=channelViewsToSend.keyAt(a);
        final TLRPC.TL_messages_getMessagesViews req=new TLRPC.TL_messages_getMessagesViews();
        req.peer=getInputPeer(key);
        req.id=channelViewsToSend.valueAt(a);
        req.increment=a == 0;
        ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
          if (response != null) {
            TLRPC.Vector vector=(TLRPC.Vector)response;
            final SparseArray<SparseIntArray> channelViews=new SparseArray<>();
            SparseIntArray array=channelViews.get(key);
            if (array == null) {
              array=new SparseIntArray();
              channelViews.put(key,array);
            }
            for (int a1=0; a1 < req.id.size(); a1++) {
              if (a1 >= vector.objects.size()) {
                break;
              }
              array.put(req.id.get(a1),(Integer)vector.objects.get(a1));
            }
            MessagesStorage.getInstance(currentAccount).putChannelViews(channelViews,req.peer instanceof TLRPC.TL_inputPeerChannel);
            AndroidUtilities.runOnUIThread(() -> NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.didUpdatedMessagesViews,channelViews));
          }
        }
);
      }
      channelViewsToSend.clear();
    }
    if (pollsToCheckSize > 0) {
      AndroidUtilities.runOnUIThread(() -> {
        long time=SystemClock.uptimeMillis();
        for (int a=0, N=pollsToCheck.size(); a < N; a++) {
          SparseArray<MessageObject> array=pollsToCheck.valueAt(a);
          if (array == null) {
            continue;
          }
          for (int b=0, N2=array.size(); b < N2; b++) {
            MessageObject messageObject=array.valueAt(b);
            if (Math.abs(time - messageObject.pollLastCheckTime) < 30000) {
              if (!messageObject.pollVisibleOnScreen) {
                array.remove(messageObject.getId());
                N2--;
                b--;
              }
            }
 else {
              messageObject.pollLastCheckTime=time;
              TLRPC.TL_messages_getPollResults req=new TLRPC.TL_messages_getPollResults();
              req.peer=getInputPeer((int)messageObject.getDialogId());
              req.msg_id=messageObject.getId();
              ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
                if (error == null) {
                  processUpdates((TLRPC.Updates)response,false);
                }
              }
);
            }
          }
          if (array.size() == 0) {
            pollsToCheck.remove(pollsToCheck.keyAt(a));
            N--;
            a--;
          }
        }
        pollsToCheckSize=pollsToCheck.size();
      }
);
    }
  }
  if (!onlinePrivacy.isEmpty()) {
    ArrayList<Integer> toRemove=null;
    int currentServerTime=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
    for (    ConcurrentHashMap.Entry<Integer,Integer> entry : onlinePrivacy.entrySet()) {
      if (entry.getValue() < currentServerTime - 30) {
        if (toRemove == null) {
          toRemove=new ArrayList<>();
        }
        toRemove.add(entry.getKey());
      }
    }
    if (toRemove != null) {
      for (      Integer uid : toRemove) {
        onlinePrivacy.remove(uid);
      }
      AndroidUtilities.runOnUIThread(() -> NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.updateInterfaces,UPDATE_MASK_STATUS));
    }
  }
  if (shortPollChannels.size() != 0) {
    for (int a=0; a < shortPollChannels.size(); a++) {
      int key=shortPollChannels.keyAt(a);
      int timeout=shortPollChannels.valueAt(a);
      if (timeout < System.currentTimeMillis() / 1000) {
        shortPollChannels.delete(key);
        a--;
        if (needShortPollChannels.indexOfKey(key) >= 0) {
          getChannelDifference(key);
        }
      }
    }
  }
  if (shortPollOnlines.size() != 0) {
    long time=SystemClock.uptimeMillis() / 1000;
    for (int a=0; a < shortPollOnlines.size(); a++) {
      int key=shortPollOnlines.keyAt(a);
      int timeout=shortPollOnlines.valueAt(a);
      if (timeout < time) {
        if (needShortPollChannels.indexOfKey(key) >= 0) {
          shortPollOnlines.put(key,(int)(time + 60 * 5));
        }
 else {
          shortPollOnlines.delete(key);
          a--;
        }
        TLRPC.TL_messages_getOnlines req=new TLRPC.TL_messages_getOnlines();
        req.peer=getInputPeer(-key);
        ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
          if (response != null) {
            TLRPC.TL_chatOnlines res=(TLRPC.TL_chatOnlines)response;
            MessagesStorage.getInstance(currentAccount).updateChatOnlineCount(key,res.onlines);
            AndroidUtilities.runOnUIThread(() -> NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.chatOnlineCountDidLoad,key,res.onlines));
          }
        }
);
      }
    }
  }
  if (!printingUsers.isEmpty() || lastPrintingStringCount != printingUsers.size()) {
    boolean updated=false;
    ArrayList<Long> keys=new ArrayList<>(printingUsers.keySet());
    for (int b=0; b < keys.size(); b++) {
      long key=keys.get(b);
      ArrayList<PrintingUser> arr=printingUsers.get(key);
      if (arr != null) {
        for (int a=0; a < arr.size(); a++) {
          PrintingUser user=arr.get(a);
          int timeToRemove;
          if (user.action instanceof TLRPC.TL_sendMessageGamePlayAction) {
            timeToRemove=30000;
          }
 else {
            timeToRemove=5900;
          }
          if (user.lastTime + timeToRemove < currentTime) {
            updated=true;
            arr.remove(user);
            a--;
          }
        }
      }
      if (arr == null || arr.isEmpty()) {
        printingUsers.remove(key);
        keys.remove(b);
        b--;
      }
    }
    updatePrintingStrings();
    if (updated) {
      AndroidUtilities.runOnUIThread(() -> NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.updateInterfaces,UPDATE_MASK_USER_PRINT));
    }
  }
  if (Theme.selectedAutoNightType == Theme.AUTO_NIGHT_TYPE_SCHEDULED && Math.abs(currentTime - lastThemeCheckTime) >= 60) {
    AndroidUtilities.runOnUIThread(themeCheckRunnable);
    lastThemeCheckTime=currentTime;
  }
  if (UserConfig.getInstance(currentAccount).savedPasswordHash != null && Math.abs(currentTime - lastPasswordCheckTime) >= 60) {
    AndroidUtilities.runOnUIThread(passwordCheckRunnable);
    lastPasswordCheckTime=currentTime;
  }
  if (lastPushRegisterSendTime != 0 && Math.abs(SystemClock.elapsedRealtime() - lastPushRegisterSendTime) >= 3 * 60 * 60 * 1000) {
    GcmPushListenerService.sendRegistrationToServer(SharedConfig.pushString);
  }
  LocationController.getInstance(currentAccount).update();
  checkProxyInfoInternal(false);
  checkTosUpdate();
}

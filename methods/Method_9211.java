@Override public boolean onFragmentCreate(){
  super.onFragmentCreate();
  if (getArguments() != null) {
    onlySelect=arguments.getBoolean("onlySelect",false);
    cantSendToChannels=arguments.getBoolean("cantSendToChannels",false);
    dialogsType=arguments.getInt("dialogsType",0);
    selectAlertString=arguments.getString("selectAlertString");
    selectAlertStringGroup=arguments.getString("selectAlertStringGroup");
    addToGroupAlertString=arguments.getString("addToGroupAlertString");
    allowSwitchAccount=arguments.getBoolean("allowSwitchAccount");
    checkCanWrite=arguments.getBoolean("checkCanWrite",true);
    folderId=arguments.getInt("folderId",0);
  }
  if (dialogsType == 0) {
    askAboutContacts=MessagesController.getGlobalNotificationsSettings().getBoolean("askAboutContacts",true);
    SharedConfig.loadProxyList();
  }
  if (searchString == null) {
    currentConnectionState=getConnectionsManager().getConnectionState();
    getNotificationCenter().addObserver(this,NotificationCenter.dialogsNeedReload);
    NotificationCenter.getGlobalInstance().addObserver(this,NotificationCenter.emojiDidLoad);
    if (!onlySelect) {
      NotificationCenter.getGlobalInstance().addObserver(this,NotificationCenter.closeSearchByActiveAction);
      NotificationCenter.getGlobalInstance().addObserver(this,NotificationCenter.proxySettingsChanged);
    }
    getNotificationCenter().addObserver(this,NotificationCenter.updateInterfaces);
    getNotificationCenter().addObserver(this,NotificationCenter.encryptedChatUpdated);
    getNotificationCenter().addObserver(this,NotificationCenter.contactsDidLoad);
    getNotificationCenter().addObserver(this,NotificationCenter.appDidLogout);
    getNotificationCenter().addObserver(this,NotificationCenter.openedChatChanged);
    getNotificationCenter().addObserver(this,NotificationCenter.notificationsSettingsUpdated);
    getNotificationCenter().addObserver(this,NotificationCenter.messageReceivedByAck);
    getNotificationCenter().addObserver(this,NotificationCenter.messageReceivedByServer);
    getNotificationCenter().addObserver(this,NotificationCenter.messageSendError);
    getNotificationCenter().addObserver(this,NotificationCenter.needReloadRecentDialogsSearch);
    getNotificationCenter().addObserver(this,NotificationCenter.replyMessagesDidLoad);
    getNotificationCenter().addObserver(this,NotificationCenter.reloadHints);
    getNotificationCenter().addObserver(this,NotificationCenter.didUpdateConnectionState);
    getNotificationCenter().addObserver(this,NotificationCenter.dialogsUnreadCounterChanged);
    getNotificationCenter().addObserver(this,NotificationCenter.needDeleteDialog);
    getNotificationCenter().addObserver(this,NotificationCenter.folderBecomeEmpty);
    NotificationCenter.getGlobalInstance().addObserver(this,NotificationCenter.didSetPasscode);
  }
  if (!dialogsLoaded[currentAccount]) {
    getMessagesController().loadGlobalNotificationsSettings();
    getMessagesController().loadDialogs(folderId,0,100,true);
    getMessagesController().loadHintDialogs();
    getContactsController().checkInviteText();
    getDataQuery().loadRecents(DataQuery.TYPE_FAVE,false,true,false);
    getDataQuery().checkFeaturedStickers();
    dialogsLoaded[currentAccount]=true;
  }
  getMessagesController().loadPinnedDialogs(folderId,0,null);
  return true;
}

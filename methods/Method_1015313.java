@Override public void reset(){
  chkNotify.setSelected(settings.getState().NOTIFY_MSG);
  chkNotifyGroupMsg.setSelected(settings.getState().NOTIFY_GROUP_MSG);
  chkSendBtn.setSelected(settings.getState().SHOW_SEND);
  chkNotifyUnread.setSelected(settings.getState().NOTIFY_UNREAD);
  chkNotifyUnknown.setSelected(settings.getState().NOTIFY_UNKNOWN);
  chkHideMyInput.setSelected(settings.getState().HIDE_MY_INPUT);
  chkHistory.setSelected(settings.getState().LOG_HISTORY);
  comboSend.setSelectedItem(settings.getState().KEY_SEND);
}

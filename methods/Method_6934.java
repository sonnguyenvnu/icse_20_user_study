private static int getUpdateChannelId(TLRPC.Update update){
  if (update instanceof TLRPC.TL_updateNewChannelMessage) {
    return ((TLRPC.TL_updateNewChannelMessage)update).message.to_id.channel_id;
  }
 else   if (update instanceof TLRPC.TL_updateEditChannelMessage) {
    return ((TLRPC.TL_updateEditChannelMessage)update).message.to_id.channel_id;
  }
 else   if (update instanceof TLRPC.TL_updateReadChannelOutbox) {
    return ((TLRPC.TL_updateReadChannelOutbox)update).channel_id;
  }
 else   if (update instanceof TLRPC.TL_updateChannelMessageViews) {
    return ((TLRPC.TL_updateChannelMessageViews)update).channel_id;
  }
 else   if (update instanceof TLRPC.TL_updateChannelTooLong) {
    return ((TLRPC.TL_updateChannelTooLong)update).channel_id;
  }
 else   if (update instanceof TLRPC.TL_updateChannelPinnedMessage) {
    return ((TLRPC.TL_updateChannelPinnedMessage)update).channel_id;
  }
 else   if (update instanceof TLRPC.TL_updateChannelReadMessagesContents) {
    return ((TLRPC.TL_updateChannelReadMessagesContents)update).channel_id;
  }
 else   if (update instanceof TLRPC.TL_updateChannelAvailableMessages) {
    return ((TLRPC.TL_updateChannelAvailableMessages)update).channel_id;
  }
 else   if (update instanceof TLRPC.TL_updateChannel) {
    return ((TLRPC.TL_updateChannel)update).channel_id;
  }
 else   if (update instanceof TLRPC.TL_updateChannelWebPage) {
    return ((TLRPC.TL_updateChannelWebPage)update).channel_id;
  }
 else   if (update instanceof TLRPC.TL_updateDeleteChannelMessages) {
    return ((TLRPC.TL_updateDeleteChannelMessages)update).channel_id;
  }
 else   if (update instanceof TLRPC.TL_updateReadChannelInbox) {
    return ((TLRPC.TL_updateReadChannelInbox)update).channel_id;
  }
 else {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.e("trying to get unknown update channel_id for " + update);
    }
    return 0;
  }
}

public void processLoadedChannelAdmins(final ArrayList<Integer> array,final int chatId,final boolean cache){
  Collections.sort(array);
  if (!cache) {
    MessagesStorage.getInstance(currentAccount).putChannelAdmins(chatId,array);
  }
  AndroidUtilities.runOnUIThread(() -> {
    loadingChannelAdmins.delete(chatId);
    channelAdmins.put(chatId,array);
    if (cache) {
      loadChannelAdmins(chatId,false);
    }
  }
);
}

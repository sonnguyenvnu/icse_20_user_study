private void putEmojiKeywords(String lang,TLRPC.TL_emojiKeywordsDifference res){
  if (res == null) {
    return;
  }
  MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
    try {
      if (!res.keywords.isEmpty()) {
        SQLitePreparedStatement insertState=MessagesStorage.getInstance(currentAccount).getDatabase().executeFast("REPLACE INTO emoji_keywords_v2 VALUES(?, ?, ?)");
        SQLitePreparedStatement deleteState=MessagesStorage.getInstance(currentAccount).getDatabase().executeFast("DELETE FROM emoji_keywords_v2 WHERE lang = ? AND keyword = ? AND emoji = ?");
        MessagesStorage.getInstance(currentAccount).getDatabase().beginTransaction();
        for (int a=0, N=res.keywords.size(); a < N; a++) {
          TLRPC.EmojiKeyword keyword=res.keywords.get(a);
          if (keyword instanceof TLRPC.TL_emojiKeyword) {
            TLRPC.TL_emojiKeyword emojiKeyword=(TLRPC.TL_emojiKeyword)keyword;
            String key=emojiKeyword.keyword.toLowerCase();
            for (int b=0, N2=emojiKeyword.emoticons.size(); b < N2; b++) {
              insertState.requery();
              insertState.bindString(1,res.lang_code);
              insertState.bindString(2,key);
              insertState.bindString(3,emojiKeyword.emoticons.get(b));
              insertState.step();
            }
          }
 else           if (keyword instanceof TLRPC.TL_emojiKeywordDeleted) {
            TLRPC.TL_emojiKeywordDeleted keywordDeleted=(TLRPC.TL_emojiKeywordDeleted)keyword;
            String key=keywordDeleted.keyword.toLowerCase();
            for (int b=0, N2=keywordDeleted.emoticons.size(); b < N2; b++) {
              deleteState.requery();
              deleteState.bindString(1,res.lang_code);
              deleteState.bindString(2,key);
              deleteState.bindString(3,keywordDeleted.emoticons.get(b));
              deleteState.step();
            }
          }
        }
        MessagesStorage.getInstance(currentAccount).getDatabase().commitTransaction();
        insertState.dispose();
        deleteState.dispose();
      }
      SQLitePreparedStatement infoState=MessagesStorage.getInstance(currentAccount).getDatabase().executeFast("REPLACE INTO emoji_keywords_info_v2 VALUES(?, ?, ?, ?)");
      infoState.bindString(1,lang);
      infoState.bindString(2,res.lang_code);
      infoState.bindInteger(3,res.version);
      infoState.bindLong(4,System.currentTimeMillis());
      infoState.step();
      infoState.dispose();
      AndroidUtilities.runOnUIThread(() -> {
        currentFetchingEmoji.remove(lang);
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.newEmojiSuggestionsAvailable,lang);
      }
);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}

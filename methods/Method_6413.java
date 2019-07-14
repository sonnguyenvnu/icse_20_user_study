public void getEmojiSuggestions(String[] langCodes,String keyword,boolean fullMatch,KeywordResultCallback callback,CountDownLatch sync){
  if (callback == null) {
    return;
  }
  if (TextUtils.isEmpty(keyword) || langCodes == null) {
    callback.run(new ArrayList<>(),null);
    return;
  }
  ArrayList<String> recentEmoji=new ArrayList<>(Emoji.recentEmoji);
  MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
    ArrayList<KeywordResult> result=new ArrayList();
    HashMap<String,Boolean> resultMap=new HashMap<>();
    String alias=null;
    try {
      SQLiteCursor cursor;
      boolean hasAny=false;
      for (int a=0; a < langCodes.length; a++) {
        cursor=MessagesStorage.getInstance(currentAccount).getDatabase().queryFinalized("SELECT alias FROM emoji_keywords_info_v2 WHERE lang = ?",langCodes[a]);
        if (cursor.next()) {
          alias=cursor.stringValue(0);
        }
        cursor.dispose();
        if (alias != null) {
          hasAny=true;
        }
      }
      if (!hasAny) {
        AndroidUtilities.runOnUIThread(() -> {
          for (int a=0; a < langCodes.length; a++) {
            if (currentFetchingEmoji.get(langCodes[a]) != null) {
              return;
            }
          }
          callback.run(result,null);
        }
);
        return;
      }
      String key=keyword.toLowerCase();
      for (int a=0; a < 2; a++) {
        if (a == 1) {
          String translitKey=LocaleController.getInstance().getTranslitString(key,false,false);
          if (translitKey.equals(key)) {
            continue;
          }
          key=translitKey;
        }
        String key2=null;
        StringBuilder nextKey=new StringBuilder(key);
        int pos=nextKey.length();
        while (pos > 0) {
          pos--;
          char value=nextKey.charAt(pos);
          value++;
          nextKey.setCharAt(pos,value);
          if (value != 0) {
            key2=nextKey.toString();
            break;
          }
        }
        if (fullMatch) {
          cursor=MessagesStorage.getInstance(currentAccount).getDatabase().queryFinalized("SELECT emoji, keyword FROM emoji_keywords_v2 WHERE keyword = ?",key);
        }
 else         if (key2 != null) {
          cursor=MessagesStorage.getInstance(currentAccount).getDatabase().queryFinalized("SELECT emoji, keyword FROM emoji_keywords_v2 WHERE keyword >= ? AND keyword < ?",key,key2);
        }
 else {
          key+="%";
          cursor=MessagesStorage.getInstance(currentAccount).getDatabase().queryFinalized("SELECT emoji, keyword FROM emoji_keywords_v2 WHERE keyword LIKE ?",key);
        }
        while (cursor.next()) {
          String value=cursor.stringValue(0).replace("\ufe0f","");
          if (resultMap.get(value) != null) {
            continue;
          }
          resultMap.put(value,true);
          KeywordResult keywordResult=new KeywordResult();
          keywordResult.emoji=value;
          keywordResult.keyword=cursor.stringValue(1);
          result.add(keywordResult);
        }
        cursor.dispose();
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    Collections.sort(result,(o1,o2) -> {
      int idx1=recentEmoji.indexOf(o1.emoji);
      if (idx1 < 0) {
        idx1=Integer.MAX_VALUE;
      }
      int idx2=recentEmoji.indexOf(o2.emoji);
      if (idx2 < 0) {
        idx2=Integer.MAX_VALUE;
      }
      if (idx1 < idx2) {
        return -1;
      }
 else       if (idx1 > idx2) {
        return 1;
      }
 else {
        int len1=o1.keyword.length();
        int len2=o2.keyword.length();
        if (len1 < len2) {
          return -1;
        }
 else         if (len1 > len2) {
          return 1;
        }
        return 0;
      }
    }
);
    String aliasFinal=alias;
    if (sync != null) {
      callback.run(result,aliasFinal);
      sync.countDown();
    }
 else {
      AndroidUtilities.runOnUIThread(() -> callback.run(result,aliasFinal));
    }
  }
);
  if (sync != null) {
    try {
      sync.await();
    }
 catch (    Throwable ignore) {
    }
  }
}

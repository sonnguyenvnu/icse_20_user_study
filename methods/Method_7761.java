private void searchDialogsInternal(final String query,final int searchId){
  if (needMessagesSearch == 2) {
    return;
  }
  MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
    try {
      String savedMessages=LocaleController.getString("SavedMessages",R.string.SavedMessages).toLowerCase();
      String search1=query.trim().toLowerCase();
      if (search1.length() == 0) {
        lastSearchId=-1;
        updateSearchResults(new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),lastSearchId);
        return;
      }
      String search2=LocaleController.getInstance().getTranslitString(search1);
      if (search1.equals(search2) || search2.length() == 0) {
        search2=null;
      }
      String[] search=new String[1 + (search2 != null ? 1 : 0)];
      search[0]=search1;
      if (search2 != null) {
        search[1]=search2;
      }
      ArrayList<Integer> usersToLoad=new ArrayList<>();
      ArrayList<Integer> chatsToLoad=new ArrayList<>();
      ArrayList<Integer> encryptedToLoad=new ArrayList<>();
      ArrayList<TLRPC.User> encUsers=new ArrayList<>();
      int resultCount=0;
      LongSparseArray<DialogSearchResult> dialogsResult=new LongSparseArray<>();
      SQLiteCursor cursor=MessagesStorage.getInstance(currentAccount).getDatabase().queryFinalized("SELECT did, date FROM dialogs ORDER BY date DESC LIMIT 600");
      while (cursor.next()) {
        long id=cursor.longValue(0);
        DialogSearchResult dialogSearchResult=new DialogSearchResult();
        dialogSearchResult.date=cursor.intValue(1);
        dialogsResult.put(id,dialogSearchResult);
        int lower_id=(int)id;
        int high_id=(int)(id >> 32);
        if (lower_id != 0) {
          if (high_id == 1) {
            if (dialogsType == 0 && !chatsToLoad.contains(lower_id)) {
              chatsToLoad.add(lower_id);
            }
          }
 else {
            if (lower_id > 0) {
              if (dialogsType != 2 && !usersToLoad.contains(lower_id)) {
                usersToLoad.add(lower_id);
              }
            }
 else {
              if (!chatsToLoad.contains(-lower_id)) {
                chatsToLoad.add(-lower_id);
              }
            }
          }
        }
 else         if (dialogsType == 0 || dialogsType == 3) {
          if (!encryptedToLoad.contains(high_id)) {
            encryptedToLoad.add(high_id);
          }
        }
      }
      cursor.dispose();
      if (savedMessages.startsWith(search1)) {
        TLRPC.User user=UserConfig.getInstance(currentAccount).getCurrentUser();
        DialogSearchResult dialogSearchResult=new DialogSearchResult();
        dialogSearchResult.date=Integer.MAX_VALUE;
        dialogSearchResult.name=savedMessages;
        dialogSearchResult.object=user;
        dialogsResult.put((long)user.id,dialogSearchResult);
        resultCount++;
      }
      if (!usersToLoad.isEmpty()) {
        cursor=MessagesStorage.getInstance(currentAccount).getDatabase().queryFinalized(String.format(Locale.US,"SELECT data, status, name FROM users WHERE uid IN(%s)",TextUtils.join(",",usersToLoad)));
        while (cursor.next()) {
          String name=cursor.stringValue(2);
          String tName=LocaleController.getInstance().getTranslitString(name);
          if (name.equals(tName)) {
            tName=null;
          }
          String username=null;
          int usernamePos=name.lastIndexOf(";;;");
          if (usernamePos != -1) {
            username=name.substring(usernamePos + 3);
          }
          int found=0;
          for (          String q : search) {
            if (name.startsWith(q) || name.contains(" " + q) || tName != null && (tName.startsWith(q) || tName.contains(" " + q))) {
              found=1;
            }
 else             if (username != null && username.startsWith(q)) {
              found=2;
            }
            if (found != 0) {
              NativeByteBuffer data=cursor.byteBufferValue(0);
              if (data != null) {
                TLRPC.User user=TLRPC.User.TLdeserialize(data,data.readInt32(false),false);
                data.reuse();
                DialogSearchResult dialogSearchResult=dialogsResult.get((long)user.id);
                if (user.status != null) {
                  user.status.expires=cursor.intValue(1);
                }
                if (found == 1) {
                  dialogSearchResult.name=AndroidUtilities.generateSearchName(user.first_name,user.last_name,q);
                }
 else {
                  dialogSearchResult.name=AndroidUtilities.generateSearchName("@" + user.username,null,"@" + q);
                }
                dialogSearchResult.object=user;
                resultCount++;
              }
              break;
            }
          }
        }
        cursor.dispose();
      }
      if (!chatsToLoad.isEmpty()) {
        cursor=MessagesStorage.getInstance(currentAccount).getDatabase().queryFinalized(String.format(Locale.US,"SELECT data, name FROM chats WHERE uid IN(%s)",TextUtils.join(",",chatsToLoad)));
        while (cursor.next()) {
          String name=cursor.stringValue(1);
          String tName=LocaleController.getInstance().getTranslitString(name);
          if (name.equals(tName)) {
            tName=null;
          }
          for (          String q : search) {
            if (name.startsWith(q) || name.contains(" " + q) || tName != null && (tName.startsWith(q) || tName.contains(" " + q))) {
              NativeByteBuffer data=cursor.byteBufferValue(0);
              if (data != null) {
                TLRPC.Chat chat=TLRPC.Chat.TLdeserialize(data,data.readInt32(false),false);
                data.reuse();
                if (!(chat == null || chat.deactivated || ChatObject.isChannel(chat) && ChatObject.isNotInChat(chat))) {
                  long dialog_id;
                  if (chat.id > 0) {
                    dialog_id=-chat.id;
                  }
 else {
                    dialog_id=AndroidUtilities.makeBroadcastId(chat.id);
                  }
                  DialogSearchResult dialogSearchResult=dialogsResult.get(dialog_id);
                  dialogSearchResult.name=AndroidUtilities.generateSearchName(chat.title,null,q);
                  dialogSearchResult.object=chat;
                  resultCount++;
                }
              }
              break;
            }
          }
        }
        cursor.dispose();
      }
      if (!encryptedToLoad.isEmpty()) {
        cursor=MessagesStorage.getInstance(currentAccount).getDatabase().queryFinalized(String.format(Locale.US,"SELECT q.data, u.name, q.user, q.g, q.authkey, q.ttl, u.data, u.status, q.layer, q.seq_in, q.seq_out, q.use_count, q.exchange_id, q.key_date, q.fprint, q.fauthkey, q.khash, q.in_seq_no, q.admin_id, q.mtproto_seq FROM enc_chats as q INNER JOIN users as u ON q.user = u.uid WHERE q.uid IN(%s)",TextUtils.join(",",encryptedToLoad)));
        while (cursor.next()) {
          String name=cursor.stringValue(1);
          String tName=LocaleController.getInstance().getTranslitString(name);
          if (name.equals(tName)) {
            tName=null;
          }
          String username=null;
          int usernamePos=name.lastIndexOf(";;;");
          if (usernamePos != -1) {
            username=name.substring(usernamePos + 2);
          }
          int found=0;
          for (int a=0; a < search.length; a++) {
            String q=search[a];
            if (name.startsWith(q) || name.contains(" " + q) || tName != null && (tName.startsWith(q) || tName.contains(" " + q))) {
              found=1;
            }
 else             if (username != null && username.startsWith(q)) {
              found=2;
            }
            if (found != 0) {
              TLRPC.EncryptedChat chat=null;
              TLRPC.User user=null;
              NativeByteBuffer data=cursor.byteBufferValue(0);
              if (data != null) {
                chat=TLRPC.EncryptedChat.TLdeserialize(data,data.readInt32(false),false);
                data.reuse();
              }
              data=cursor.byteBufferValue(6);
              if (data != null) {
                user=TLRPC.User.TLdeserialize(data,data.readInt32(false),false);
                data.reuse();
              }
              if (chat != null && user != null) {
                DialogSearchResult dialogSearchResult=dialogsResult.get((long)chat.id << 32);
                chat.user_id=cursor.intValue(2);
                chat.a_or_b=cursor.byteArrayValue(3);
                chat.auth_key=cursor.byteArrayValue(4);
                chat.ttl=cursor.intValue(5);
                chat.layer=cursor.intValue(8);
                chat.seq_in=cursor.intValue(9);
                chat.seq_out=cursor.intValue(10);
                int use_count=cursor.intValue(11);
                chat.key_use_count_in=(short)(use_count >> 16);
                chat.key_use_count_out=(short)(use_count);
                chat.exchange_id=cursor.longValue(12);
                chat.key_create_date=cursor.intValue(13);
                chat.future_key_fingerprint=cursor.longValue(14);
                chat.future_auth_key=cursor.byteArrayValue(15);
                chat.key_hash=cursor.byteArrayValue(16);
                chat.in_seq_no=cursor.intValue(17);
                int admin_id=cursor.intValue(18);
                if (admin_id != 0) {
                  chat.admin_id=admin_id;
                }
                chat.mtproto_seq=cursor.intValue(19);
                if (user.status != null) {
                  user.status.expires=cursor.intValue(7);
                }
                if (found == 1) {
                  dialogSearchResult.name=new SpannableStringBuilder(ContactsController.formatName(user.first_name,user.last_name));
                  ((SpannableStringBuilder)dialogSearchResult.name).setSpan(new ForegroundColorSpan(Theme.getColor(Theme.key_chats_secretName)),0,dialogSearchResult.name.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
 else {
                  dialogSearchResult.name=AndroidUtilities.generateSearchName("@" + user.username,null,"@" + q);
                }
                dialogSearchResult.object=chat;
                encUsers.add(user);
                resultCount++;
              }
              break;
            }
          }
        }
        cursor.dispose();
      }
      ArrayList<DialogSearchResult> searchResults=new ArrayList<>(resultCount);
      for (int a=0; a < dialogsResult.size(); a++) {
        DialogSearchResult dialogSearchResult=dialogsResult.valueAt(a);
        if (dialogSearchResult.object != null && dialogSearchResult.name != null) {
          searchResults.add(dialogSearchResult);
        }
      }
      Collections.sort(searchResults,(lhs,rhs) -> {
        if (lhs.date < rhs.date) {
          return 1;
        }
 else         if (lhs.date > rhs.date) {
          return -1;
        }
        return 0;
      }
);
      ArrayList<TLObject> resultArray=new ArrayList<>();
      ArrayList<CharSequence> resultArrayNames=new ArrayList<>();
      for (int a=0; a < searchResults.size(); a++) {
        DialogSearchResult dialogSearchResult=searchResults.get(a);
        resultArray.add(dialogSearchResult.object);
        resultArrayNames.add(dialogSearchResult.name);
      }
      if (dialogsType != 2) {
        cursor=MessagesStorage.getInstance(currentAccount).getDatabase().queryFinalized("SELECT u.data, u.status, u.name, u.uid FROM users as u INNER JOIN contacts as c ON u.uid = c.uid");
        while (cursor.next()) {
          int uid=cursor.intValue(3);
          if (dialogsResult.indexOfKey((long)uid) >= 0) {
            continue;
          }
          String name=cursor.stringValue(2);
          String tName=LocaleController.getInstance().getTranslitString(name);
          if (name.equals(tName)) {
            tName=null;
          }
          String username=null;
          int usernamePos=name.lastIndexOf(";;;");
          if (usernamePos != -1) {
            username=name.substring(usernamePos + 3);
          }
          int found=0;
          for (          String q : search) {
            if (name.startsWith(q) || name.contains(" " + q) || tName != null && (tName.startsWith(q) || tName.contains(" " + q))) {
              found=1;
            }
 else             if (username != null && username.startsWith(q)) {
              found=2;
            }
            if (found != 0) {
              NativeByteBuffer data=cursor.byteBufferValue(0);
              if (data != null) {
                TLRPC.User user=TLRPC.User.TLdeserialize(data,data.readInt32(false),false);
                data.reuse();
                if (user.status != null) {
                  user.status.expires=cursor.intValue(1);
                }
                if (found == 1) {
                  resultArrayNames.add(AndroidUtilities.generateSearchName(user.first_name,user.last_name,q));
                }
 else {
                  resultArrayNames.add(AndroidUtilities.generateSearchName("@" + user.username,null,"@" + q));
                }
                resultArray.add(user);
              }
              break;
            }
          }
        }
        cursor.dispose();
      }
      updateSearchResults(resultArray,resultArrayNames,encUsers,searchId);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}

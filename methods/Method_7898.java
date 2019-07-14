@Override public View createView(Context context){
  actionBar.setBackButtonImage(R.drawable.ic_ab_back);
  actionBar.setAllowOverlayTitle(true);
  actionBar.setTitle(LocaleController.getString("StorageUsage",R.string.StorageUsage));
  actionBar.setActionBarMenuOnItemClick(new ActionBar.ActionBarMenuOnItemClick(){
    @Override public void onItemClick(    int id){
      if (id == -1) {
        finishFragment();
      }
    }
  }
);
  listAdapter=new ListAdapter(context);
  fragmentView=new FrameLayout(context);
  FrameLayout frameLayout=(FrameLayout)fragmentView;
  frameLayout.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
  listView=new RecyclerListView(context);
  listView.setVerticalScrollBarEnabled(false);
  listView.setLayoutManager(layoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
  frameLayout.addView(listView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
  listView.setAdapter(listAdapter);
  listView.setOnItemClickListener((view,position) -> {
    if (getParentActivity() == null) {
      return;
    }
    if (position == keepMediaRow) {
      BottomSheet.Builder builder=new BottomSheet.Builder(getParentActivity());
      builder.setItems(new CharSequence[]{LocaleController.formatPluralString("Days",3),LocaleController.formatPluralString("Weeks",1),LocaleController.formatPluralString("Months",1),LocaleController.getString("KeepMediaForever",R.string.KeepMediaForever)},(dialog,which) -> {
        SharedPreferences.Editor editor=MessagesController.getGlobalMainSettings().edit();
        if (which == 0) {
          editor.putInt("keep_media",3);
        }
 else         if (which == 1) {
          editor.putInt("keep_media",0);
        }
 else         if (which == 2) {
          editor.putInt("keep_media",1);
        }
 else         if (which == 3) {
          editor.putInt("keep_media",2);
        }
        editor.commit();
        if (listAdapter != null) {
          listAdapter.notifyDataSetChanged();
        }
        PendingIntent pintent=PendingIntent.getService(ApplicationLoader.applicationContext,1,new Intent(ApplicationLoader.applicationContext,ClearCacheService.class),0);
        AlarmManager alarmManager=(AlarmManager)ApplicationLoader.applicationContext.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pintent);
        if (which != 3) {
          alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,0,AlarmManager.INTERVAL_DAY,pintent);
        }
      }
);
      showDialog(builder.create());
    }
 else     if (position == databaseRow) {
      AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
      builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
      builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
      builder.setMessage(LocaleController.getString("LocalDatabaseClear",R.string.LocalDatabaseClear));
      builder.setPositiveButton(LocaleController.getString("CacheClear",R.string.CacheClear),(dialogInterface,i) -> {
        final AlertDialog progressDialog=new AlertDialog(getParentActivity(),3);
        progressDialog.setCanCacnel(false);
        progressDialog.show();
        MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
          try {
            SQLiteDatabase database=MessagesStorage.getInstance(currentAccount).getDatabase();
            ArrayList<Long> dialogsToCleanup=new ArrayList<>();
            SQLiteCursor cursor=database.queryFinalized("SELECT did FROM dialogs WHERE 1");
            StringBuilder ids=new StringBuilder();
            while (cursor.next()) {
              long did=cursor.longValue(0);
              int lower_id=(int)did;
              int high_id=(int)(did >> 32);
              if (lower_id != 0 && high_id != 1) {
                dialogsToCleanup.add(did);
              }
            }
            cursor.dispose();
            SQLitePreparedStatement state5=database.executeFast("REPLACE INTO messages_holes VALUES(?, ?, ?)");
            SQLitePreparedStatement state6=database.executeFast("REPLACE INTO media_holes_v2 VALUES(?, ?, ?, ?)");
            database.beginTransaction();
            for (int a=0; a < dialogsToCleanup.size(); a++) {
              Long did=dialogsToCleanup.get(a);
              int messagesCount=0;
              cursor=database.queryFinalized("SELECT COUNT(mid) FROM messages WHERE uid = " + did);
              if (cursor.next()) {
                messagesCount=cursor.intValue(0);
              }
              cursor.dispose();
              if (messagesCount <= 2) {
                continue;
              }
              cursor=database.queryFinalized("SELECT last_mid_i, last_mid FROM dialogs WHERE did = " + did);
              int messageId=-1;
              if (cursor.next()) {
                long last_mid_i=cursor.longValue(0);
                long last_mid=cursor.longValue(1);
                SQLiteCursor cursor2=database.queryFinalized("SELECT data FROM messages WHERE uid = " + did + " AND mid IN (" + last_mid_i + "," + last_mid + ")");
                try {
                  while (cursor2.next()) {
                    NativeByteBuffer data=cursor2.byteBufferValue(0);
                    if (data != null) {
                      TLRPC.Message message=TLRPC.Message.TLdeserialize(data,data.readInt32(false),false);
                      message.readAttachPath(data,UserConfig.getInstance(currentAccount).clientUserId);
                      data.reuse();
                      if (message != null) {
                        messageId=message.id;
                      }
                    }
                  }
                }
 catch (                Exception e) {
                  FileLog.e(e);
                }
                cursor2.dispose();
                database.executeFast("DELETE FROM messages WHERE uid = " + did + " AND mid != " + last_mid_i + " AND mid != " + last_mid).stepThis().dispose();
                database.executeFast("DELETE FROM messages_holes WHERE uid = " + did).stepThis().dispose();
                database.executeFast("DELETE FROM bot_keyboard WHERE uid = " + did).stepThis().dispose();
                database.executeFast("DELETE FROM media_counts_v2 WHERE uid = " + did).stepThis().dispose();
                database.executeFast("DELETE FROM media_v2 WHERE uid = " + did).stepThis().dispose();
                database.executeFast("DELETE FROM media_holes_v2 WHERE uid = " + did).stepThis().dispose();
                DataQuery.getInstance(currentAccount).clearBotKeyboard(did,null);
                if (messageId != -1) {
                  MessagesStorage.createFirstHoles(did,state5,state6,messageId);
                }
              }
              cursor.dispose();
            }
            state5.dispose();
            state6.dispose();
            database.commitTransaction();
            database.executeFast("PRAGMA journal_size_limit = 0").stepThis().dispose();
            database.executeFast("VACUUM").stepThis().dispose();
            database.executeFast("PRAGMA journal_size_limit = -1").stepThis().dispose();
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
 finally {
            AndroidUtilities.runOnUIThread(() -> {
              try {
                progressDialog.dismiss();
              }
 catch (              Exception e) {
                FileLog.e(e);
              }
              if (listAdapter != null) {
                databaseSize=MessagesStorage.getInstance(currentAccount).getDatabaseSize();
                listAdapter.notifyDataSetChanged();
              }
            }
);
          }
        }
);
      }
);
      showDialog(builder.create());
    }
 else     if (position == cacheRow) {
      if (totalSize <= 0 || getParentActivity() == null) {
        return;
      }
      BottomSheet.Builder builder=new BottomSheet.Builder(getParentActivity());
      builder.setApplyTopPadding(false);
      builder.setApplyBottomPadding(false);
      LinearLayout linearLayout=new LinearLayout(getParentActivity());
      linearLayout.setOrientation(LinearLayout.VERTICAL);
      for (int a=0; a < 6; a++) {
        long size=0;
        String name=null;
        if (a == 0) {
          size=photoSize;
          name=LocaleController.getString("LocalPhotoCache",R.string.LocalPhotoCache);
        }
 else         if (a == 1) {
          size=videoSize;
          name=LocaleController.getString("LocalVideoCache",R.string.LocalVideoCache);
        }
 else         if (a == 2) {
          size=documentsSize;
          name=LocaleController.getString("LocalDocumentCache",R.string.LocalDocumentCache);
        }
 else         if (a == 3) {
          size=musicSize;
          name=LocaleController.getString("LocalMusicCache",R.string.LocalMusicCache);
        }
 else         if (a == 4) {
          size=audioSize;
          name=LocaleController.getString("LocalAudioCache",R.string.LocalAudioCache);
        }
 else         if (a == 5) {
          size=cacheSize;
          name=LocaleController.getString("LocalCache",R.string.LocalCache);
        }
        if (size > 0) {
          clear[a]=true;
          CheckBoxCell checkBoxCell=new CheckBoxCell(getParentActivity(),1,21);
          checkBoxCell.setTag(a);
          checkBoxCell.setBackgroundDrawable(Theme.getSelectorDrawable(false));
          linearLayout.addView(checkBoxCell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,50));
          checkBoxCell.setText(name,AndroidUtilities.formatFileSize(size),true,true);
          checkBoxCell.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
          checkBoxCell.setOnClickListener(v -> {
            CheckBoxCell cell=(CheckBoxCell)v;
            int num=(Integer)cell.getTag();
            clear[num]=!clear[num];
            cell.setChecked(clear[num],true);
          }
);
        }
 else {
          clear[a]=false;
        }
      }
      BottomSheet.BottomSheetCell cell=new BottomSheet.BottomSheetCell(getParentActivity(),1);
      cell.setBackgroundDrawable(Theme.getSelectorDrawable(false));
      cell.setTextAndIcon(LocaleController.getString("ClearMediaCache",R.string.ClearMediaCache).toUpperCase(),0);
      cell.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteRedText));
      cell.setOnClickListener(v -> {
        try {
          if (visibleDialog != null) {
            visibleDialog.dismiss();
          }
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
        cleanupFolders();
      }
);
      linearLayout.addView(cell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,50));
      builder.setCustomView(linearLayout);
      showDialog(builder.create());
    }
  }
);
  return fragmentView;
}

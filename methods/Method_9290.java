private void runLinkRequest(final int intentAccount,final String username,final String group,final String sticker,final String botUser,final String botChat,final String message,final boolean hasUrl,final Integer messageId,final Integer channelId,final String game,final HashMap<String,String> auth,final String lang,final String unsupportedUrl,final String code,final TLRPC.TL_wallPaper wallPaper,final int state){
  if (state == 0 && UserConfig.getActivatedAccountsCount() >= 2 && auth != null) {
    AlertsCreator.createAccountSelectDialog(this,account -> {
      if (account != intentAccount) {
        switchToAccount(account,true);
      }
      runLinkRequest(account,username,group,sticker,botUser,botChat,message,hasUrl,messageId,channelId,game,auth,lang,unsupportedUrl,code,wallPaper,1);
    }
).show();
    return;
  }
 else   if (code != null) {
    if (NotificationCenter.getGlobalInstance().hasObservers(NotificationCenter.didReceiveSmsCode)) {
      NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didReceiveSmsCode,code);
    }
 else {
      AlertDialog.Builder builder=new AlertDialog.Builder(LaunchActivity.this);
      builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
      builder.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("OtherLoginCode",R.string.OtherLoginCode,code)));
      builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
      showAlertDialog(builder);
    }
    return;
  }
  final AlertDialog progressDialog=new AlertDialog(this,3);
  final int[] requestId=new int[]{0};
  if (username != null) {
    TLRPC.TL_contacts_resolveUsername req=new TLRPC.TL_contacts_resolveUsername();
    req.username=username;
    requestId[0]=ConnectionsManager.getInstance(intentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
      if (!LaunchActivity.this.isFinishing()) {
        try {
          progressDialog.dismiss();
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
        final TLRPC.TL_contacts_resolvedPeer res=(TLRPC.TL_contacts_resolvedPeer)response;
        if (error == null && actionBarLayout != null && (game == null || game != null && !res.users.isEmpty())) {
          MessagesController.getInstance(intentAccount).putUsers(res.users,false);
          MessagesController.getInstance(intentAccount).putChats(res.chats,false);
          MessagesStorage.getInstance(intentAccount).putUsersAndChats(res.users,res.chats,false,true);
          if (game != null) {
            Bundle args=new Bundle();
            args.putBoolean("onlySelect",true);
            args.putBoolean("cantSendToChannels",true);
            args.putInt("dialogsType",1);
            args.putString("selectAlertString",LocaleController.getString("SendGameTo",R.string.SendGameTo));
            args.putString("selectAlertStringGroup",LocaleController.getString("SendGameToGroup",R.string.SendGameToGroup));
            DialogsActivity fragment=new DialogsActivity(args);
            fragment.setDelegate((fragment1,dids,message1,param) -> {
              long did=dids.get(0);
              TLRPC.TL_inputMediaGame inputMediaGame=new TLRPC.TL_inputMediaGame();
              inputMediaGame.id=new TLRPC.TL_inputGameShortName();
              inputMediaGame.id.short_name=game;
              inputMediaGame.id.bot_id=MessagesController.getInstance(intentAccount).getInputUser(res.users.get(0));
              SendMessagesHelper.getInstance(intentAccount).sendGame(MessagesController.getInstance(intentAccount).getInputPeer((int)did),inputMediaGame,0,0);
              Bundle args1=new Bundle();
              args1.putBoolean("scrollToTopOnResume",true);
              int lower_part=(int)did;
              int high_id=(int)(did >> 32);
              if (lower_part != 0) {
                if (high_id == 1) {
                  args1.putInt("chat_id",lower_part);
                }
 else {
                  if (lower_part > 0) {
                    args1.putInt("user_id",lower_part);
                  }
 else                   if (lower_part < 0) {
                    args1.putInt("chat_id",-lower_part);
                  }
                }
              }
 else {
                args1.putInt("enc_id",high_id);
              }
              if (MessagesController.getInstance(intentAccount).checkCanOpenChat(args1,fragment1)) {
                NotificationCenter.getInstance(intentAccount).postNotificationName(NotificationCenter.closeChats);
                actionBarLayout.presentFragment(new ChatActivity(args1),true,false,true,false);
              }
            }
);
            boolean removeLast;
            if (AndroidUtilities.isTablet()) {
              removeLast=layersActionBarLayout.fragmentsStack.size() > 0 && layersActionBarLayout.fragmentsStack.get(layersActionBarLayout.fragmentsStack.size() - 1) instanceof DialogsActivity;
            }
 else {
              removeLast=actionBarLayout.fragmentsStack.size() > 1 && actionBarLayout.fragmentsStack.get(actionBarLayout.fragmentsStack.size() - 1) instanceof DialogsActivity;
            }
            actionBarLayout.presentFragment(fragment,removeLast,true,true,false);
            if (SecretMediaViewer.hasInstance() && SecretMediaViewer.getInstance().isVisible()) {
              SecretMediaViewer.getInstance().closePhoto(false,false);
            }
 else             if (PhotoViewer.hasInstance() && PhotoViewer.getInstance().isVisible()) {
              PhotoViewer.getInstance().closePhoto(false,true);
            }
 else             if (ArticleViewer.hasInstance() && ArticleViewer.getInstance().isVisible()) {
              ArticleViewer.getInstance().close(false,true);
            }
            drawerLayoutContainer.setAllowOpenDrawer(false,false);
            if (AndroidUtilities.isTablet()) {
              actionBarLayout.showLastFragment();
              rightActionBarLayout.showLastFragment();
            }
 else {
              drawerLayoutContainer.setAllowOpenDrawer(true,false);
            }
          }
 else           if (botChat != null) {
            final TLRPC.User user=!res.users.isEmpty() ? res.users.get(0) : null;
            if (user == null || user.bot && user.bot_nochats) {
              try {
                Toast.makeText(LaunchActivity.this,LocaleController.getString("BotCantJoinGroups",R.string.BotCantJoinGroups),Toast.LENGTH_SHORT).show();
              }
 catch (              Exception e) {
                FileLog.e(e);
              }
              return;
            }
            Bundle args=new Bundle();
            args.putBoolean("onlySelect",true);
            args.putInt("dialogsType",2);
            args.putString("addToGroupAlertString",LocaleController.formatString("AddToTheGroupTitle",R.string.AddToTheGroupTitle,UserObject.getUserName(user),"%1$s"));
            DialogsActivity fragment=new DialogsActivity(args);
            fragment.setDelegate((fragment12,dids,message1,param) -> {
              long did=dids.get(0);
              Bundle args12=new Bundle();
              args12.putBoolean("scrollToTopOnResume",true);
              args12.putInt("chat_id",-(int)did);
              if (mainFragmentsStack.isEmpty() || MessagesController.getInstance(intentAccount).checkCanOpenChat(args12,mainFragmentsStack.get(mainFragmentsStack.size() - 1))) {
                NotificationCenter.getInstance(intentAccount).postNotificationName(NotificationCenter.closeChats);
                MessagesController.getInstance(intentAccount).addUserToChat(-(int)did,user,null,0,botChat,null,null);
                actionBarLayout.presentFragment(new ChatActivity(args12),true,false,true,false);
              }
            }
);
            presentFragment(fragment);
          }
 else {
            long dialog_id;
            boolean isBot=false;
            Bundle args=new Bundle();
            if (!res.chats.isEmpty()) {
              args.putInt("chat_id",res.chats.get(0).id);
              dialog_id=-res.chats.get(0).id;
            }
 else {
              args.putInt("user_id",res.users.get(0).id);
              dialog_id=res.users.get(0).id;
            }
            if (botUser != null && res.users.size() > 0 && res.users.get(0).bot) {
              args.putString("botUser",botUser);
              isBot=true;
            }
            if (messageId != null) {
              args.putInt("message_id",messageId);
            }
            BaseFragment lastFragment=!mainFragmentsStack.isEmpty() ? mainFragmentsStack.get(mainFragmentsStack.size() - 1) : null;
            if (lastFragment == null || MessagesController.getInstance(intentAccount).checkCanOpenChat(args,lastFragment)) {
              if (isBot && lastFragment instanceof ChatActivity && ((ChatActivity)lastFragment).getDialogId() == dialog_id) {
                ((ChatActivity)lastFragment).setBotUser(botUser);
              }
 else {
                ChatActivity fragment=new ChatActivity(args);
                actionBarLayout.presentFragment(fragment);
              }
            }
          }
        }
 else {
          try {
            Toast.makeText(LaunchActivity.this,LocaleController.getString("NoUsernameFound",R.string.NoUsernameFound),Toast.LENGTH_SHORT).show();
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
        }
      }
    }
));
  }
 else   if (group != null) {
    if (state == 0) {
      final TLRPC.TL_messages_checkChatInvite req=new TLRPC.TL_messages_checkChatInvite();
      req.hash=group;
      requestId[0]=ConnectionsManager.getInstance(intentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
        if (!LaunchActivity.this.isFinishing()) {
          try {
            progressDialog.dismiss();
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
          if (error == null && actionBarLayout != null) {
            TLRPC.ChatInvite invite=(TLRPC.ChatInvite)response;
            if (invite.chat != null && (!ChatObject.isLeftFromChat(invite.chat) || !invite.chat.kicked && !TextUtils.isEmpty(invite.chat.username))) {
              MessagesController.getInstance(intentAccount).putChat(invite.chat,false);
              ArrayList<TLRPC.Chat> chats=new ArrayList<>();
              chats.add(invite.chat);
              MessagesStorage.getInstance(intentAccount).putUsersAndChats(null,chats,false,true);
              Bundle args=new Bundle();
              args.putInt("chat_id",invite.chat.id);
              if (mainFragmentsStack.isEmpty() || MessagesController.getInstance(intentAccount).checkCanOpenChat(args,mainFragmentsStack.get(mainFragmentsStack.size() - 1))) {
                ChatActivity fragment=new ChatActivity(args);
                NotificationCenter.getInstance(intentAccount).postNotificationName(NotificationCenter.closeChats);
                actionBarLayout.presentFragment(fragment,false,true,true,false);
              }
            }
 else {
              if ((invite.chat == null && (!invite.channel || invite.megagroup) || invite.chat != null && (!ChatObject.isChannel(invite.chat) || invite.chat.megagroup)) && !mainFragmentsStack.isEmpty()) {
                BaseFragment fragment=mainFragmentsStack.get(mainFragmentsStack.size() - 1);
                fragment.showDialog(new JoinGroupAlert(LaunchActivity.this,invite,group,fragment));
              }
 else {
                AlertDialog.Builder builder=new AlertDialog.Builder(LaunchActivity.this);
                builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
                builder.setMessage(LocaleController.formatString("ChannelJoinTo",R.string.ChannelJoinTo,invite.chat != null ? invite.chat.title : invite.title));
                builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),(dialogInterface,i) -> runLinkRequest(intentAccount,username,group,sticker,botUser,botChat,message,hasUrl,messageId,channelId,game,auth,lang,unsupportedUrl,code,wallPaper,1));
                builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
                showAlertDialog(builder);
              }
            }
          }
 else {
            AlertDialog.Builder builder=new AlertDialog.Builder(LaunchActivity.this);
            builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
            if (error.text.startsWith("FLOOD_WAIT")) {
              builder.setMessage(LocaleController.getString("FloodWait",R.string.FloodWait));
            }
 else {
              builder.setMessage(LocaleController.getString("JoinToGroupErrorNotExist",R.string.JoinToGroupErrorNotExist));
            }
            builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
            showAlertDialog(builder);
          }
        }
      }
),ConnectionsManager.RequestFlagFailOnServerErrors);
    }
 else     if (state == 1) {
      TLRPC.TL_messages_importChatInvite req=new TLRPC.TL_messages_importChatInvite();
      req.hash=group;
      ConnectionsManager.getInstance(intentAccount).sendRequest(req,(response,error) -> {
        if (error == null) {
          TLRPC.Updates updates=(TLRPC.Updates)response;
          MessagesController.getInstance(intentAccount).processUpdates(updates,false);
        }
        AndroidUtilities.runOnUIThread(() -> {
          if (!LaunchActivity.this.isFinishing()) {
            try {
              progressDialog.dismiss();
            }
 catch (            Exception e) {
              FileLog.e(e);
            }
            if (error == null) {
              if (actionBarLayout != null) {
                TLRPC.Updates updates=(TLRPC.Updates)response;
                if (!updates.chats.isEmpty()) {
                  TLRPC.Chat chat=updates.chats.get(0);
                  chat.left=false;
                  chat.kicked=false;
                  MessagesController.getInstance(intentAccount).putUsers(updates.users,false);
                  MessagesController.getInstance(intentAccount).putChats(updates.chats,false);
                  Bundle args=new Bundle();
                  args.putInt("chat_id",chat.id);
                  if (mainFragmentsStack.isEmpty() || MessagesController.getInstance(intentAccount).checkCanOpenChat(args,mainFragmentsStack.get(mainFragmentsStack.size() - 1))) {
                    ChatActivity fragment=new ChatActivity(args);
                    NotificationCenter.getInstance(intentAccount).postNotificationName(NotificationCenter.closeChats);
                    actionBarLayout.presentFragment(fragment,false,true,true,false);
                  }
                }
              }
            }
 else {
              AlertDialog.Builder builder=new AlertDialog.Builder(LaunchActivity.this);
              builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
              if (error.text.startsWith("FLOOD_WAIT")) {
                builder.setMessage(LocaleController.getString("FloodWait",R.string.FloodWait));
              }
 else               if (error.text.equals("USERS_TOO_MUCH")) {
                builder.setMessage(LocaleController.getString("JoinToGroupErrorFull",R.string.JoinToGroupErrorFull));
              }
 else {
                builder.setMessage(LocaleController.getString("JoinToGroupErrorNotExist",R.string.JoinToGroupErrorNotExist));
              }
              builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
              showAlertDialog(builder);
            }
          }
        }
);
      }
,ConnectionsManager.RequestFlagFailOnServerErrors);
    }
  }
 else   if (sticker != null) {
    if (!mainFragmentsStack.isEmpty()) {
      TLRPC.TL_inputStickerSetShortName stickerset=new TLRPC.TL_inputStickerSetShortName();
      stickerset.short_name=sticker;
      BaseFragment fragment=mainFragmentsStack.get(mainFragmentsStack.size() - 1);
      fragment.showDialog(new StickersAlert(LaunchActivity.this,fragment,stickerset,null,null));
    }
    return;
  }
 else   if (message != null) {
    Bundle args=new Bundle();
    args.putBoolean("onlySelect",true);
    DialogsActivity fragment=new DialogsActivity(args);
    fragment.setDelegate((fragment13,dids,m,param) -> {
      long did=dids.get(0);
      Bundle args13=new Bundle();
      args13.putBoolean("scrollToTopOnResume",true);
      args13.putBoolean("hasUrl",hasUrl);
      int lower_part=(int)did;
      int high_id=(int)(did >> 32);
      if (lower_part != 0) {
        if (high_id == 1) {
          args13.putInt("chat_id",lower_part);
        }
 else {
          if (lower_part > 0) {
            args13.putInt("user_id",lower_part);
          }
 else           if (lower_part < 0) {
            args13.putInt("chat_id",-lower_part);
          }
        }
      }
 else {
        args13.putInt("enc_id",high_id);
      }
      if (MessagesController.getInstance(intentAccount).checkCanOpenChat(args13,fragment13)) {
        NotificationCenter.getInstance(intentAccount).postNotificationName(NotificationCenter.closeChats);
        DataQuery.getInstance(intentAccount).saveDraft(did,message,null,null,false);
        actionBarLayout.presentFragment(new ChatActivity(args13),true,false,true,false);
      }
    }
);
    presentFragment(fragment,false,true);
  }
 else   if (auth != null) {
    final int bot_id=Utilities.parseInt(auth.get("bot_id"));
    if (bot_id == 0) {
      return;
    }
    final String payload=auth.get("payload");
    final String nonce=auth.get("nonce");
    final String callbackUrl=auth.get("callback_url");
    final TLRPC.TL_account_getAuthorizationForm req=new TLRPC.TL_account_getAuthorizationForm();
    req.bot_id=bot_id;
    req.scope=auth.get("scope");
    req.public_key=auth.get("public_key");
    requestId[0]=ConnectionsManager.getInstance(intentAccount).sendRequest(req,(response,error) -> {
      final TLRPC.TL_account_authorizationForm authorizationForm=(TLRPC.TL_account_authorizationForm)response;
      if (authorizationForm != null) {
        TLRPC.TL_account_getPassword req2=new TLRPC.TL_account_getPassword();
        requestId[0]=ConnectionsManager.getInstance(intentAccount).sendRequest(req2,(response1,error1) -> AndroidUtilities.runOnUIThread(() -> {
          try {
            progressDialog.dismiss();
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
          if (response1 != null) {
            TLRPC.TL_account_password accountPassword=(TLRPC.TL_account_password)response1;
            MessagesController.getInstance(intentAccount).putUsers(authorizationForm.users,false);
            presentFragment(new PassportActivity(PassportActivity.TYPE_PASSWORD,req.bot_id,req.scope,req.public_key,payload,nonce,callbackUrl,authorizationForm,accountPassword));
          }
        }
));
      }
 else {
        AndroidUtilities.runOnUIThread(() -> {
          try {
            progressDialog.dismiss();
            if ("APP_VERSION_OUTDATED".equals(error.text)) {
              AlertsCreator.showUpdateAppAlert(LaunchActivity.this,LocaleController.getString("UpdateAppAlert",R.string.UpdateAppAlert),true);
            }
 else {
              showAlertDialog(AlertsCreator.createSimpleAlert(LaunchActivity.this,LocaleController.getString("ErrorOccurred",R.string.ErrorOccurred) + "\n" + error.text));
            }
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
        }
);
      }
    }
);
  }
 else   if (unsupportedUrl != null) {
    TLRPC.TL_help_getDeepLinkInfo req=new TLRPC.TL_help_getDeepLinkInfo();
    req.path=unsupportedUrl;
    requestId[0]=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
      try {
        progressDialog.dismiss();
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
      if (response instanceof TLRPC.TL_help_deepLinkInfo) {
        TLRPC.TL_help_deepLinkInfo res=(TLRPC.TL_help_deepLinkInfo)response;
        AlertsCreator.showUpdateAppAlert(LaunchActivity.this,res.message,res.update_app);
      }
    }
));
  }
 else   if (lang != null) {
    TLRPC.TL_langpack_getLanguage req=new TLRPC.TL_langpack_getLanguage();
    req.lang_code=lang;
    req.lang_pack="android";
    requestId[0]=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
      try {
        progressDialog.dismiss();
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
      if (response instanceof TLRPC.TL_langPackLanguage) {
        TLRPC.TL_langPackLanguage res=(TLRPC.TL_langPackLanguage)response;
        showAlertDialog(AlertsCreator.createLanguageAlert(LaunchActivity.this,res));
      }
 else       if (error != null) {
        if ("LANG_CODE_NOT_SUPPORTED".equals(error.text)) {
          showAlertDialog(AlertsCreator.createSimpleAlert(LaunchActivity.this,LocaleController.getString("LanguageUnsupportedError",R.string.LanguageUnsupportedError)));
        }
 else {
          showAlertDialog(AlertsCreator.createSimpleAlert(LaunchActivity.this,LocaleController.getString("ErrorOccurred",R.string.ErrorOccurred) + "\n" + error.text));
        }
      }
    }
));
  }
 else   if (wallPaper != null) {
    boolean ok=false;
    if (TextUtils.isEmpty(wallPaper.slug)) {
      try {
        WallpapersListActivity.ColorWallpaper colorWallpaper=new WallpapersListActivity.ColorWallpaper(-100,wallPaper.settings.background_color);
        WallpaperActivity wallpaperActivity=new WallpaperActivity(colorWallpaper,null);
        AndroidUtilities.runOnUIThread(() -> presentFragment(wallpaperActivity));
        ok=true;
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
    if (!ok) {
      TLRPC.TL_account_getWallPaper req=new TLRPC.TL_account_getWallPaper();
      TLRPC.TL_inputWallPaperSlug inputWallPaperSlug=new TLRPC.TL_inputWallPaperSlug();
      inputWallPaperSlug.slug=wallPaper.slug;
      req.wallpaper=inputWallPaperSlug;
      requestId[0]=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
        try {
          progressDialog.dismiss();
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
        if (response instanceof TLRPC.TL_wallPaper) {
          TLRPC.TL_wallPaper res=(TLRPC.TL_wallPaper)response;
          Object object;
          if (res.pattern) {
            WallpapersListActivity.ColorWallpaper colorWallpaper=new WallpapersListActivity.ColorWallpaper(-1,wallPaper.settings.background_color,res.id,wallPaper.settings.intensity / 100.0f,wallPaper.settings.motion,null);
            colorWallpaper.pattern=res;
            object=colorWallpaper;
          }
 else {
            object=res;
          }
          WallpaperActivity wallpaperActivity=new WallpaperActivity(object,null);
          wallpaperActivity.setInitialModes(wallPaper.settings.blur,wallPaper.settings.motion);
          presentFragment(wallpaperActivity);
        }
 else {
          showAlertDialog(AlertsCreator.createSimpleAlert(LaunchActivity.this,LocaleController.getString("ErrorOccurred",R.string.ErrorOccurred) + "\n" + error.text));
        }
      }
));
    }
  }
 else   if (channelId != null && messageId != null) {
    Bundle args=new Bundle();
    args.putInt("chat_id",channelId);
    args.putInt("message_id",messageId);
    BaseFragment lastFragment=!mainFragmentsStack.isEmpty() ? mainFragmentsStack.get(mainFragmentsStack.size() - 1) : null;
    if (lastFragment == null || MessagesController.getInstance(intentAccount).checkCanOpenChat(args,lastFragment)) {
      AndroidUtilities.runOnUIThread(() -> {
        if (!actionBarLayout.presentFragment(new ChatActivity(args))) {
          TLRPC.TL_channels_getChannels req=new TLRPC.TL_channels_getChannels();
          TLRPC.TL_inputChannel inputChannel=new TLRPC.TL_inputChannel();
          inputChannel.channel_id=channelId;
          req.id.add(inputChannel);
          requestId[0]=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
            try {
              progressDialog.dismiss();
            }
 catch (            Exception e) {
              FileLog.e(e);
            }
            boolean notFound=true;
            if (response instanceof TLRPC.TL_messages_chats) {
              TLRPC.TL_messages_chats res=(TLRPC.TL_messages_chats)response;
              if (!res.chats.isEmpty()) {
                notFound=false;
                MessagesController.getInstance(currentAccount).putChats(res.chats,false);
                TLRPC.Chat chat=res.chats.get(0);
                if (lastFragment == null || MessagesController.getInstance(intentAccount).checkCanOpenChat(args,lastFragment)) {
                  actionBarLayout.presentFragment(new ChatActivity(args));
                }
              }
            }
            if (notFound) {
              showAlertDialog(AlertsCreator.createSimpleAlert(LaunchActivity.this,LocaleController.getString("LinkNotFound",R.string.LinkNotFound)));
            }
          }
));
        }
      }
);
    }
  }
  if (requestId[0] != 0) {
    progressDialog.setOnCancelListener(dialog -> ConnectionsManager.getInstance(intentAccount).cancelRequest(requestId[0],true));
    try {
      progressDialog.show();
    }
 catch (    Exception ignore) {
    }
  }
}

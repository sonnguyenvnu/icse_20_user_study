public void sendCallback(final boolean cache,final MessageObject messageObject,final TLRPC.KeyboardButton button,final ChatActivity parentFragment){
  if (messageObject == null || button == null || parentFragment == null) {
    return;
  }
  final boolean cacheFinal;
  int type;
  if (button instanceof TLRPC.TL_keyboardButtonUrlAuth) {
    cacheFinal=false;
    type=3;
  }
 else   if (button instanceof TLRPC.TL_keyboardButtonGame) {
    cacheFinal=false;
    type=1;
  }
 else {
    cacheFinal=cache;
    if (button instanceof TLRPC.TL_keyboardButtonBuy) {
      type=2;
    }
 else {
      type=0;
    }
  }
  final String key=messageObject.getDialogId() + "_" + messageObject.getId() + "_" + Utilities.bytesToHex(button.data) + "_" + type;
  waitingForCallback.put(key,true);
  TLObject[] request=new TLObject[1];
  RequestDelegate requestDelegate=(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    waitingForCallback.remove(key);
    if (cacheFinal && response == null) {
      sendCallback(false,messageObject,button,parentFragment);
    }
 else     if (response != null) {
      if (button instanceof TLRPC.TL_keyboardButtonUrlAuth) {
        if (response instanceof TLRPC.TL_urlAuthResultRequest) {
          TLRPC.TL_urlAuthResultRequest res=(TLRPC.TL_urlAuthResultRequest)response;
          parentFragment.showRequestUrlAlert(res,(TLRPC.TL_messages_requestUrlAuth)request[0],button.url);
        }
 else         if (response instanceof TLRPC.TL_urlAuthResultAccepted) {
          TLRPC.TL_urlAuthResultAccepted res=(TLRPC.TL_urlAuthResultAccepted)response;
          parentFragment.showOpenUrlAlert(res.url,false);
        }
 else         if (response instanceof TLRPC.TL_urlAuthResultDefault) {
          TLRPC.TL_urlAuthResultDefault res=(TLRPC.TL_urlAuthResultDefault)response;
          parentFragment.showOpenUrlAlert(button.url,true);
        }
      }
 else       if (button instanceof TLRPC.TL_keyboardButtonBuy) {
        if (response instanceof TLRPC.TL_payments_paymentForm) {
          final TLRPC.TL_payments_paymentForm form=(TLRPC.TL_payments_paymentForm)response;
          MessagesController.getInstance(currentAccount).putUsers(form.users,false);
          parentFragment.presentFragment(new PaymentFormActivity(form,messageObject));
        }
 else         if (response instanceof TLRPC.TL_payments_paymentReceipt) {
          parentFragment.presentFragment(new PaymentFormActivity(messageObject,(TLRPC.TL_payments_paymentReceipt)response));
        }
      }
 else {
        TLRPC.TL_messages_botCallbackAnswer res=(TLRPC.TL_messages_botCallbackAnswer)response;
        if (!cacheFinal && res.cache_time != 0) {
          MessagesStorage.getInstance(currentAccount).saveBotCache(key,res);
        }
        if (res.message != null) {
          int uid=messageObject.messageOwner.from_id;
          if (messageObject.messageOwner.via_bot_id != 0) {
            uid=messageObject.messageOwner.via_bot_id;
          }
          String name=null;
          if (uid > 0) {
            TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(uid);
            if (user != null) {
              name=ContactsController.formatName(user.first_name,user.last_name);
            }
          }
 else {
            TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(-uid);
            if (chat != null) {
              name=chat.title;
            }
          }
          if (name == null) {
            name="bot";
          }
          if (res.alert) {
            if (parentFragment.getParentActivity() == null) {
              return;
            }
            AlertDialog.Builder builder=new AlertDialog.Builder(parentFragment.getParentActivity());
            builder.setTitle(name);
            builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
            builder.setMessage(res.message);
            parentFragment.showDialog(builder.create());
          }
 else {
            parentFragment.showAlert(name,res.message);
          }
        }
 else         if (res.url != null) {
          if (parentFragment.getParentActivity() == null) {
            return;
          }
          int uid=messageObject.messageOwner.from_id;
          if (messageObject.messageOwner.via_bot_id != 0) {
            uid=messageObject.messageOwner.via_bot_id;
          }
          TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(uid);
          boolean verified=user != null && user.verified;
          if (button instanceof TLRPC.TL_keyboardButtonGame) {
            TLRPC.TL_game game=messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaGame ? messageObject.messageOwner.media.game : null;
            if (game == null) {
              return;
            }
            parentFragment.showOpenGameAlert(game,messageObject,res.url,!verified && MessagesController.getNotificationsSettings(currentAccount).getBoolean("askgame_" + uid,true),uid);
          }
 else {
            parentFragment.showOpenUrlAlert(res.url,false);
          }
        }
      }
    }
  }
);
  if (cacheFinal) {
    MessagesStorage.getInstance(currentAccount).getBotCache(key,requestDelegate);
  }
 else {
    if (button instanceof TLRPC.TL_keyboardButtonUrlAuth) {
      TLRPC.TL_messages_requestUrlAuth req=new TLRPC.TL_messages_requestUrlAuth();
      req.peer=MessagesController.getInstance(currentAccount).getInputPeer((int)messageObject.getDialogId());
      req.msg_id=messageObject.getId();
      req.button_id=button.button_id;
      request[0]=req;
      ConnectionsManager.getInstance(currentAccount).sendRequest(req,requestDelegate,ConnectionsManager.RequestFlagFailOnServerErrors);
    }
 else     if (button instanceof TLRPC.TL_keyboardButtonBuy) {
      if ((messageObject.messageOwner.media.flags & 4) == 0) {
        TLRPC.TL_payments_getPaymentForm req=new TLRPC.TL_payments_getPaymentForm();
        req.msg_id=messageObject.getId();
        ConnectionsManager.getInstance(currentAccount).sendRequest(req,requestDelegate,ConnectionsManager.RequestFlagFailOnServerErrors);
      }
 else {
        TLRPC.TL_payments_getPaymentReceipt req=new TLRPC.TL_payments_getPaymentReceipt();
        req.msg_id=messageObject.messageOwner.media.receipt_msg_id;
        ConnectionsManager.getInstance(currentAccount).sendRequest(req,requestDelegate,ConnectionsManager.RequestFlagFailOnServerErrors);
      }
    }
 else {
      TLRPC.TL_messages_getBotCallbackAnswer req=new TLRPC.TL_messages_getBotCallbackAnswer();
      req.peer=MessagesController.getInstance(currentAccount).getInputPeer((int)messageObject.getDialogId());
      req.msg_id=messageObject.getId();
      req.game=button instanceof TLRPC.TL_keyboardButtonGame;
      if (button.data != null) {
        req.flags|=1;
        req.data=button.data;
      }
      ConnectionsManager.getInstance(currentAccount).sendRequest(req,requestDelegate,ConnectionsManager.RequestFlagFailOnServerErrors);
    }
  }
}

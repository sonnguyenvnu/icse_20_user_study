public void didPressedBotButton(final TLRPC.KeyboardButton button,final MessageObject replyMessageObject,final MessageObject messageObject){
  if (button == null || messageObject == null) {
    return;
  }
  if (button instanceof TLRPC.TL_keyboardButton) {
    SendMessagesHelper.getInstance(currentAccount).sendMessage(button.text,dialog_id,replyMessageObject,null,false,null,null,null);
  }
 else   if (button instanceof TLRPC.TL_keyboardButtonUrl) {
    parentFragment.showOpenUrlAlert(button.url,true);
  }
 else   if (button instanceof TLRPC.TL_keyboardButtonRequestPhone) {
    parentFragment.shareMyContact(messageObject);
  }
 else   if (button instanceof TLRPC.TL_keyboardButtonRequestGeoLocation) {
    AlertDialog.Builder builder=new AlertDialog.Builder(parentActivity);
    builder.setTitle(LocaleController.getString("ShareYouLocationTitle",R.string.ShareYouLocationTitle));
    builder.setMessage(LocaleController.getString("ShareYouLocationInfo",R.string.ShareYouLocationInfo));
    builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),(dialogInterface,i) -> {
      if (Build.VERSION.SDK_INT >= 23 && parentActivity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        parentActivity.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},2);
        pendingMessageObject=messageObject;
        pendingLocationButton=button;
        return;
      }
      SendMessagesHelper.getInstance(currentAccount).sendCurrentLocation(messageObject,button);
    }
);
    builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
    parentFragment.showDialog(builder.create());
  }
 else   if (button instanceof TLRPC.TL_keyboardButtonCallback || button instanceof TLRPC.TL_keyboardButtonGame || button instanceof TLRPC.TL_keyboardButtonBuy || button instanceof TLRPC.TL_keyboardButtonUrlAuth) {
    SendMessagesHelper.getInstance(currentAccount).sendCallback(true,messageObject,button,parentFragment);
  }
 else   if (button instanceof TLRPC.TL_keyboardButtonSwitchInline) {
    if (parentFragment.processSwitchButton((TLRPC.TL_keyboardButtonSwitchInline)button)) {
      return;
    }
    if (button.same_peer) {
      int uid=messageObject.messageOwner.from_id;
      if (messageObject.messageOwner.via_bot_id != 0) {
        uid=messageObject.messageOwner.via_bot_id;
      }
      TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(uid);
      if (user == null) {
        return;
      }
      setFieldText("@" + user.username + " " + button.query);
    }
 else {
      Bundle args=new Bundle();
      args.putBoolean("onlySelect",true);
      args.putInt("dialogsType",1);
      DialogsActivity fragment=new DialogsActivity(args);
      fragment.setDelegate((fragment1,dids,message,param) -> {
        int uid=messageObject.messageOwner.from_id;
        if (messageObject.messageOwner.via_bot_id != 0) {
          uid=messageObject.messageOwner.via_bot_id;
        }
        TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(uid);
        if (user == null) {
          fragment1.finishFragment();
          return;
        }
        long did=dids.get(0);
        DataQuery.getInstance(currentAccount).saveDraft(did,"@" + user.username + " " + button.query,null,null,true);
        if (did != dialog_id) {
          int lower_part=(int)did;
          if (lower_part != 0) {
            Bundle args1=new Bundle();
            if (lower_part > 0) {
              args1.putInt("user_id",lower_part);
            }
 else             if (lower_part < 0) {
              args1.putInt("chat_id",-lower_part);
            }
            if (!MessagesController.getInstance(currentAccount).checkCanOpenChat(args1,fragment1)) {
              return;
            }
            ChatActivity chatActivity=new ChatActivity(args1);
            if (parentFragment.presentFragment(chatActivity,true)) {
              if (!AndroidUtilities.isTablet()) {
                parentFragment.removeSelfFromStack();
              }
            }
 else {
              fragment1.finishFragment();
            }
          }
 else {
            fragment1.finishFragment();
          }
        }
 else {
          fragment1.finishFragment();
        }
      }
);
      parentFragment.presentFragment(fragment);
    }
  }
}

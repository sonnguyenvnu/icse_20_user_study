private void createMenu(View v){
  MessageObject message=null;
  if (v instanceof ChatMessageCell) {
    message=((ChatMessageCell)v).getMessageObject();
  }
 else   if (v instanceof ChatActionCell) {
    message=((ChatActionCell)v).getMessageObject();
  }
  if (message == null) {
    return;
  }
  final int type=getMessageType(message);
  selectedObject=message;
  if (getParentActivity() == null) {
    return;
  }
  AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
  ArrayList<CharSequence> items=new ArrayList<>();
  final ArrayList<Integer> options=new ArrayList<>();
  if (selectedObject.type == 0 || selectedObject.caption != null) {
    items.add(LocaleController.getString("Copy",R.string.Copy));
    options.add(3);
  }
  if (type == 1) {
    if (selectedObject.currentEvent != null && selectedObject.currentEvent.action instanceof TLRPC.TL_channelAdminLogEventActionChangeStickerSet) {
      TLRPC.InputStickerSet stickerSet=selectedObject.currentEvent.action.new_stickerset;
      if (stickerSet == null || stickerSet instanceof TLRPC.TL_inputStickerSetEmpty) {
        stickerSet=selectedObject.currentEvent.action.prev_stickerset;
      }
      if (stickerSet != null) {
        showDialog(new StickersAlert(getParentActivity(),ChannelAdminLogActivity.this,stickerSet,null,null));
        return;
      }
    }
  }
 else   if (type == 3) {
    if (selectedObject.messageOwner.media instanceof TLRPC.TL_messageMediaWebPage && MessageObject.isNewGifDocument(selectedObject.messageOwner.media.webpage.document)) {
      items.add(LocaleController.getString("SaveToGIFs",R.string.SaveToGIFs));
      options.add(11);
    }
  }
 else   if (type == 4) {
    if (selectedObject.isVideo()) {
      items.add(LocaleController.getString("SaveToGallery",R.string.SaveToGallery));
      options.add(4);
      items.add(LocaleController.getString("ShareFile",R.string.ShareFile));
      options.add(6);
    }
 else     if (selectedObject.isMusic()) {
      items.add(LocaleController.getString("SaveToMusic",R.string.SaveToMusic));
      options.add(10);
      items.add(LocaleController.getString("ShareFile",R.string.ShareFile));
      options.add(6);
    }
 else     if (selectedObject.getDocument() != null) {
      if (MessageObject.isNewGifDocument(selectedObject.getDocument())) {
        items.add(LocaleController.getString("SaveToGIFs",R.string.SaveToGIFs));
        options.add(11);
      }
      items.add(LocaleController.getString("SaveToDownloads",R.string.SaveToDownloads));
      options.add(10);
      items.add(LocaleController.getString("ShareFile",R.string.ShareFile));
      options.add(6);
    }
 else {
      items.add(LocaleController.getString("SaveToGallery",R.string.SaveToGallery));
      options.add(4);
    }
  }
 else   if (type == 5) {
    items.add(LocaleController.getString("ApplyLocalizationFile",R.string.ApplyLocalizationFile));
    options.add(5);
    items.add(LocaleController.getString("SaveToDownloads",R.string.SaveToDownloads));
    options.add(10);
    items.add(LocaleController.getString("ShareFile",R.string.ShareFile));
    options.add(6);
  }
 else   if (type == 10) {
    items.add(LocaleController.getString("ApplyThemeFile",R.string.ApplyThemeFile));
    options.add(5);
    items.add(LocaleController.getString("SaveToDownloads",R.string.SaveToDownloads));
    options.add(10);
    items.add(LocaleController.getString("ShareFile",R.string.ShareFile));
    options.add(6);
  }
 else   if (type == 6) {
    items.add(LocaleController.getString("SaveToGallery",R.string.SaveToGallery));
    options.add(7);
    items.add(LocaleController.getString("SaveToDownloads",R.string.SaveToDownloads));
    options.add(10);
    items.add(LocaleController.getString("ShareFile",R.string.ShareFile));
    options.add(6);
  }
 else   if (type == 7) {
    if (selectedObject.isMask()) {
      items.add(LocaleController.getString("AddToMasks",R.string.AddToMasks));
    }
 else {
      items.add(LocaleController.getString("AddToStickers",R.string.AddToStickers));
    }
    options.add(9);
  }
 else   if (type == 8) {
    TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(selectedObject.messageOwner.media.user_id);
    if (user != null && user.id != UserConfig.getInstance(currentAccount).getClientUserId() && ContactsController.getInstance(currentAccount).contactsDict.get(user.id) == null) {
      items.add(LocaleController.getString("AddContactTitle",R.string.AddContactTitle));
      options.add(15);
    }
    if (selectedObject.messageOwner.media.phone_number != null || selectedObject.messageOwner.media.phone_number.length() != 0) {
      items.add(LocaleController.getString("Copy",R.string.Copy));
      options.add(16);
      items.add(LocaleController.getString("Call",R.string.Call));
      options.add(17);
    }
  }
  if (options.isEmpty()) {
    return;
  }
  final CharSequence[] finalItems=items.toArray(new CharSequence[0]);
  builder.setItems(finalItems,(dialogInterface,i) -> {
    if (selectedObject == null || i < 0 || i >= options.size()) {
      return;
    }
    processSelectedOption(options.get(i));
  }
);
  builder.setTitle(LocaleController.getString("Message",R.string.Message));
  showDialog(builder.create());
}

private void createEmojiView(){
  if (emojiView != null) {
    return;
  }
  emojiView=new EmojiView(allowStickers,allowGifs,parentActivity,true,info);
  emojiView.setVisibility(GONE);
  emojiView.setDelegate(new EmojiView.EmojiViewDelegate(){
    @Override public boolean onBackspace(){
      if (messageEditText.length() == 0) {
        return false;
      }
      messageEditText.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_DEL));
      return true;
    }
    @Override public void onEmojiSelected(    String symbol){
      int i=messageEditText.getSelectionEnd();
      if (i < 0) {
        i=0;
      }
      try {
        innerTextChange=2;
        CharSequence localCharSequence=Emoji.replaceEmoji(symbol,messageEditText.getPaint().getFontMetricsInt(),AndroidUtilities.dp(20),false);
        messageEditText.setText(messageEditText.getText().insert(i,localCharSequence));
        int j=i + localCharSequence.length();
        messageEditText.setSelection(j,j);
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
 finally {
        innerTextChange=0;
      }
    }
    @Override public void onStickerSelected(    TLRPC.Document sticker,    Object parent){
      if (stickersExpanded) {
        if (searchingType != 0) {
          searchingType=0;
          emojiView.closeSearch(true,MessageObject.getStickerSetId(sticker));
          emojiView.hideSearchKeyboard();
        }
        setStickersExpanded(false,true,false);
      }
      ChatActivityEnterView.this.onStickerSelected(sticker,parent,false);
      if ((int)dialog_id == 0 && MessageObject.isGifDocument(sticker)) {
        MessagesController.getInstance(currentAccount).saveGif(parent,sticker);
      }
    }
    @Override public void onStickersSettingsClick(){
      if (parentFragment != null) {
        parentFragment.presentFragment(new StickersActivity(DataQuery.TYPE_IMAGE));
      }
    }
    @Override public void onGifSelected(    Object gif,    Object parent){
      if (stickersExpanded) {
        if (searchingType != 0) {
          emojiView.hideSearchKeyboard();
        }
        setStickersExpanded(false,true,false);
      }
      if (gif instanceof TLRPC.Document) {
        TLRPC.Document document=(TLRPC.Document)gif;
        SendMessagesHelper.getInstance(currentAccount).sendSticker(document,dialog_id,replyingMessageObject,parent);
        DataQuery.getInstance(currentAccount).addRecentGif(document,(int)(System.currentTimeMillis() / 1000));
        if ((int)dialog_id == 0) {
          MessagesController.getInstance(currentAccount).saveGif(parent,document);
        }
      }
 else       if (gif instanceof TLRPC.BotInlineResult) {
        TLRPC.BotInlineResult result=(TLRPC.BotInlineResult)gif;
        if (result.document != null) {
          DataQuery.getInstance(currentAccount).addRecentGif(result.document,(int)(System.currentTimeMillis() / 1000));
          if ((int)dialog_id == 0) {
            MessagesController.getInstance(currentAccount).saveGif(parent,result.document);
          }
        }
        TLRPC.User bot=(TLRPC.User)parent;
        HashMap<String,String> params=new HashMap<>();
        params.put("id",result.id);
        params.put("query_id","" + result.query_id);
        SendMessagesHelper.prepareSendingBotContextResult(result,params,dialog_id,replyingMessageObject);
        if (searchingType != 0) {
          searchingType=0;
          emojiView.closeSearch(true);
          emojiView.hideSearchKeyboard();
        }
      }
      if (delegate != null) {
        delegate.onMessageSend(null);
      }
    }
    @Override public void onTabOpened(    int type){
      delegate.onStickersTab(type == 3);
      post(updateExpandabilityRunnable);
    }
    @Override public void onClearEmojiRecent(){
      if (parentFragment == null || parentActivity == null) {
        return;
      }
      AlertDialog.Builder builder=new AlertDialog.Builder(parentActivity);
      builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
      builder.setMessage(LocaleController.getString("ClearRecentEmoji",R.string.ClearRecentEmoji));
      builder.setPositiveButton(LocaleController.getString("ClearButton",R.string.ClearButton).toUpperCase(),(dialogInterface,i) -> emojiView.clearRecentEmoji());
      builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
      parentFragment.showDialog(builder.create());
    }
    @Override public void onShowStickerSet(    TLRPC.StickerSet stickerSet,    TLRPC.InputStickerSet inputStickerSet){
      if (parentFragment == null || parentActivity == null) {
        return;
      }
      if (stickerSet != null) {
        inputStickerSet=new TLRPC.TL_inputStickerSetID();
        inputStickerSet.access_hash=stickerSet.access_hash;
        inputStickerSet.id=stickerSet.id;
      }
      parentFragment.showDialog(new StickersAlert(parentActivity,parentFragment,inputStickerSet,null,ChatActivityEnterView.this));
    }
    @Override public void onStickerSetAdd(    TLRPC.StickerSetCovered stickerSet){
      DataQuery.getInstance(currentAccount).removeStickersSet(parentActivity,stickerSet.set,2,parentFragment,false);
    }
    @Override public void onStickerSetRemove(    TLRPC.StickerSetCovered stickerSet){
      DataQuery.getInstance(currentAccount).removeStickersSet(parentActivity,stickerSet.set,0,parentFragment,false);
    }
    @Override public void onStickersGroupClick(    int chatId){
      if (parentFragment != null) {
        if (AndroidUtilities.isTablet()) {
          hidePopup(false);
        }
        GroupStickersActivity fragment=new GroupStickersActivity(chatId);
        fragment.setInfo(info);
        parentFragment.presentFragment(fragment);
      }
    }
    @Override public void onSearchOpenClose(    int type){
      searchingType=type;
      setStickersExpanded(type != 0,false,false);
      if (emojiTabOpen && searchingType == 2) {
        checkStickresExpandHeight();
      }
    }
    @Override public boolean isSearchOpened(){
      return searchingType != 0;
    }
    @Override public boolean isExpanded(){
      return stickersExpanded;
    }
  }
);
  emojiView.setDragListener(new EmojiView.DragListener(){
    @Override public void onDragStart(){
      if (!allowDragging()) {
        return;
      }
      if (stickersExpansionAnim != null) {
        stickersExpansionAnim.cancel();
      }
      stickersDragging=true;
      wasExpanded=stickersExpanded;
      stickersExpanded=true;
      stickersExpandedHeight=sizeNotifierLayout.getHeight() - (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? AndroidUtilities.statusBarHeight : 0) - ActionBar.getCurrentActionBarHeight() - getHeight() + Theme.chat_composeShadowDrawable.getIntrinsicHeight();
      if (searchingType == 2) {
        stickersExpandedHeight=Math.min(stickersExpandedHeight,AndroidUtilities.dp(120) + (AndroidUtilities.displaySize.x > AndroidUtilities.displaySize.y ? keyboardHeightLand : keyboardHeight));
      }
      emojiView.getLayoutParams().height=stickersExpandedHeight;
      emojiView.setLayerType(LAYER_TYPE_HARDWARE,null);
      sizeNotifierLayout.requestLayout();
      sizeNotifierLayout.setForeground(new ScrimDrawable());
      initialOffset=(int)getTranslationY();
      if (delegate != null) {
        delegate.onStickersExpandedChange();
      }
    }
    @Override public void onDragEnd(    float velocity){
      if (!allowDragging()) {
        return;
      }
      stickersDragging=false;
      if ((wasExpanded && velocity >= AndroidUtilities.dp(200)) || (!wasExpanded && velocity <= AndroidUtilities.dp(-200)) || (wasExpanded && stickersExpansionProgress <= 0.6f) || (!wasExpanded && stickersExpansionProgress >= 0.4f)) {
        setStickersExpanded(!wasExpanded,true,true);
      }
 else {
        setStickersExpanded(wasExpanded,true,true);
      }
    }
    @Override public void onDragCancel(){
      if (!stickersTabOpen) {
        return;
      }
      stickersDragging=false;
      setStickersExpanded(wasExpanded,true,false);
    }
    @Override public void onDrag(    int offset){
      if (!allowDragging()) {
        return;
      }
      int origHeight=AndroidUtilities.displaySize.x > AndroidUtilities.displaySize.y ? keyboardHeightLand : keyboardHeight;
      offset+=initialOffset;
      offset=Math.max(Math.min(offset,0),-(stickersExpandedHeight - origHeight));
      emojiView.setTranslationY(offset);
      setTranslationY(offset);
      stickersExpansionProgress=(float)offset / (-(stickersExpandedHeight - origHeight));
      sizeNotifierLayout.invalidate();
    }
    private boolean allowDragging(){
      return stickersTabOpen && !(!stickersExpanded && messageEditText.length() > 0) && emojiView.areThereAnyStickers();
    }
  }
);
  sizeNotifierLayout.addView(emojiView);
  checkChannelRights();
}

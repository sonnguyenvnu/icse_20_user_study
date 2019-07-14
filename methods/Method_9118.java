public void showWithAction(long did,int action,Runnable actionRunnable,Runnable cancelRunnable){
  if (currentActionRunnable != null) {
    currentActionRunnable.run();
  }
  isShowed=true;
  currentActionRunnable=actionRunnable;
  currentCancelRunnable=cancelRunnable;
  currentDialogId=did;
  currentAction=action;
  timeLeft=5000;
  lastUpdateTime=SystemClock.uptimeMillis();
  if (isTooltipAction()) {
    if (action == ACTION_ARCHIVE_HIDDEN) {
      infoTextView.setText(LocaleController.getString("ArchiveHidden",R.string.ArchiveHidden));
      subinfoTextView.setText(LocaleController.getString("ArchiveHiddenInfo",R.string.ArchiveHiddenInfo));
      leftImageView.setAnimation(R.raw.chats_swipearchive);
    }
 else     if (action == ACTION_ARCHIVE_PINNED) {
      infoTextView.setText(LocaleController.getString("ArchivePinned",R.string.ArchivePinned));
      subinfoTextView.setText(LocaleController.getString("ArchivePinnedInfo",R.string.ArchivePinnedInfo));
      leftImageView.setAnimation(R.raw.chats_infotip);
    }
 else {
      if (action == ACTION_ARCHIVE_HINT) {
        infoTextView.setText(LocaleController.getString("ChatArchived",R.string.ChatArchived));
      }
 else {
        infoTextView.setText(LocaleController.getString("ChatsArchived",R.string.ChatsArchived));
      }
      subinfoTextView.setText(LocaleController.getString("ChatArchivedInfo",R.string.ChatArchivedInfo));
      leftImageView.setAnimation(R.raw.chats_infotip);
    }
    FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)infoTextView.getLayoutParams();
    layoutParams.leftMargin=AndroidUtilities.dp(58);
    layoutParams.topMargin=AndroidUtilities.dp(6);
    infoTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
    infoTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
    subinfoTextView.setVisibility(VISIBLE);
    undoButton.setVisibility(GONE);
    leftImageView.setVisibility(VISIBLE);
    leftImageView.setProgress(0);
    leftImageView.playAnimation();
  }
 else   if (currentAction == ACTION_ARCHIVE || currentAction == ACTION_ARCHIVE_FEW) {
    if (action == ACTION_ARCHIVE) {
      infoTextView.setText(LocaleController.getString("ChatArchived",R.string.ChatArchived));
    }
 else {
      infoTextView.setText(LocaleController.getString("ChatsArchived",R.string.ChatsArchived));
    }
    FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)infoTextView.getLayoutParams();
    layoutParams.leftMargin=AndroidUtilities.dp(58);
    layoutParams.topMargin=AndroidUtilities.dp(13);
    infoTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);
    undoButton.setVisibility(VISIBLE);
    infoTextView.setTypeface(Typeface.DEFAULT);
    subinfoTextView.setVisibility(GONE);
    leftImageView.setVisibility(VISIBLE);
    leftImageView.setAnimation(R.raw.chats_archived);
    leftImageView.setProgress(0);
    leftImageView.playAnimation();
  }
 else {
    FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)infoTextView.getLayoutParams();
    layoutParams.leftMargin=AndroidUtilities.dp(45);
    layoutParams.topMargin=AndroidUtilities.dp(13);
    infoTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);
    undoButton.setVisibility(VISIBLE);
    infoTextView.setTypeface(Typeface.DEFAULT);
    subinfoTextView.setVisibility(GONE);
    leftImageView.setVisibility(GONE);
    if (currentAction == ACTION_CLEAR) {
      infoTextView.setText(LocaleController.getString("HistoryClearedUndo",R.string.HistoryClearedUndo));
    }
 else {
      int lowerId=(int)did;
      if (lowerId < 0) {
        TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(-lowerId);
        if (ChatObject.isChannel(chat) && !chat.megagroup) {
          infoTextView.setText(LocaleController.getString("ChannelDeletedUndo",R.string.ChannelDeletedUndo));
        }
 else {
          infoTextView.setText(LocaleController.getString("GroupDeletedUndo",R.string.GroupDeletedUndo));
        }
      }
 else {
        infoTextView.setText(LocaleController.getString("ChatDeletedUndo",R.string.ChatDeletedUndo));
      }
    }
    MessagesController.getInstance(currentAccount).addDialogAction(did,currentAction == ACTION_CLEAR);
  }
  AndroidUtilities.makeAccessibilityAnnouncement(infoTextView.getText() + (subinfoTextView.getVisibility() == VISIBLE ? ". " + subinfoTextView.getText() : ""));
  if (getVisibility() != VISIBLE) {
    setVisibility(VISIBLE);
    setTranslationY(AndroidUtilities.dp(8 + (isTooltipAction() ? 52 : 48)));
    AnimatorSet animatorSet=new AnimatorSet();
    animatorSet.playTogether(ObjectAnimator.ofFloat(this,View.TRANSLATION_Y,AndroidUtilities.dp(8 + (isTooltipAction() ? 52 : 48)),0));
    animatorSet.setInterpolator(new DecelerateInterpolator());
    animatorSet.setDuration(180);
    animatorSet.start();
  }
}

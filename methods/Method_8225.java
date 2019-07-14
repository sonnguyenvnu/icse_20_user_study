private void initStickers(){
  if (chatActivityEnterView == null || getParentActivity() == null || stickersAdapter != null || currentEncryptedChat != null && AndroidUtilities.getPeerLayerVersion(currentEncryptedChat.layer) < 23) {
    return;
  }
  stickersListView.setPadding(AndroidUtilities.dp(18),0,AndroidUtilities.dp(18),0);
  stickersListView.setAdapter(stickersAdapter=new StickersAdapter(getParentActivity(),show -> {
    if (show && stickersPanel.getTag() != null || !show && stickersPanel.getTag() == null) {
      return;
    }
    if (show) {
      stickersListView.setPadding(AndroidUtilities.dp(18),stickersAdapter.isShowingKeywords() ? AndroidUtilities.dp(24) : 0,AndroidUtilities.dp(18),0);
      stickersListView.scrollToPosition(0);
      stickersPanel.setVisibility(allowStickersPanel ? View.VISIBLE : View.INVISIBLE);
      stickersPanel.setTag(1);
      boolean isRtl=chatActivityEnterView.isRtlText();
      FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)stickersPanelArrow.getLayoutParams();
      layoutParams.gravity=Gravity.BOTTOM | (isRtl ? Gravity.RIGHT : Gravity.LEFT);
      stickersPanelArrow.requestLayout();
    }
 else {
      stickersPanel.setTag(null);
    }
    if (runningAnimation != null) {
      runningAnimation.cancel();
      runningAnimation=null;
    }
    if (stickersPanel.getVisibility() != View.INVISIBLE) {
      runningAnimation=new AnimatorSet();
      runningAnimation.playTogether(ObjectAnimator.ofFloat(stickersPanel,View.ALPHA,show ? 0.0f : 1.0f,show ? 1.0f : 0.0f));
      runningAnimation.setDuration(150);
      runningAnimation.addListener(new AnimatorListenerAdapter(){
        @Override public void onAnimationEnd(        Animator animation){
          if (runningAnimation != null && runningAnimation.equals(animation)) {
            if (!show) {
              stickersAdapter.clearStickers();
              stickersPanel.setVisibility(View.GONE);
              if (ContentPreviewViewer.getInstance().isVisible()) {
                ContentPreviewViewer.getInstance().close();
              }
              ContentPreviewViewer.getInstance().reset();
            }
            runningAnimation=null;
          }
        }
        @Override public void onAnimationCancel(        Animator animation){
          if (runningAnimation != null && runningAnimation.equals(animation)) {
            runningAnimation=null;
          }
        }
      }
);
      runningAnimation.start();
    }
 else     if (!show) {
      stickersPanel.setVisibility(View.GONE);
    }
  }
));
  stickersListView.setOnItemClickListener(stickersOnItemClickListener=(view,position) -> {
    Object item=stickersAdapter.getItem(position);
    Object parent=stickersAdapter.getItemParent(position);
    if (item instanceof TLRPC.TL_document) {
      TLRPC.TL_document document=(TLRPC.TL_document)item;
      SendMessagesHelper.getInstance(currentAccount).sendSticker(document,dialog_id,replyingMessageObject,parent);
      hideFieldPanel(false);
      chatActivityEnterView.addStickerToRecent(document);
      chatActivityEnterView.setFieldText("");
    }
 else     if (item instanceof String) {
      String emoji=(String)item;
      SpannableString string=new SpannableString(emoji);
      Emoji.replaceEmoji(string,chatActivityEnterView.getEditField().getPaint().getFontMetricsInt(),AndroidUtilities.dp(20),false);
      stickersAdapter.loadStikersForEmoji("",false);
      chatActivityEnterView.setFieldText(string,false);
    }
  }
);
}

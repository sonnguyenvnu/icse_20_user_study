public void showStickerBanHint(boolean gif){
  if (mediaBanTooltip.getVisibility() == VISIBLE) {
    return;
  }
  TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(currentChatId);
  if (chat == null) {
    return;
  }
  String text;
  if (!ChatObject.hasAdminRights(chat) && chat.default_banned_rights != null && chat.default_banned_rights.send_stickers) {
    if (gif) {
      mediaBanTooltip.setText(LocaleController.getString("GlobalAttachGifRestricted",R.string.GlobalAttachGifRestricted));
    }
 else {
      mediaBanTooltip.setText(LocaleController.getString("GlobalAttachStickersRestricted",R.string.GlobalAttachStickersRestricted));
    }
  }
 else {
    if (chat.banned_rights == null) {
      return;
    }
    if (AndroidUtilities.isBannedForever(chat.banned_rights)) {
      if (gif) {
        mediaBanTooltip.setText(LocaleController.getString("AttachGifRestrictedForever",R.string.AttachGifRestrictedForever));
      }
 else {
        mediaBanTooltip.setText(LocaleController.getString("AttachStickersRestrictedForever",R.string.AttachStickersRestrictedForever));
      }
    }
 else {
      if (gif) {
        mediaBanTooltip.setText(LocaleController.formatString("AttachGifRestricted",R.string.AttachGifRestricted,LocaleController.formatDateForBan(chat.banned_rights.until_date)));
      }
 else {
        mediaBanTooltip.setText(LocaleController.formatString("AttachStickersRestricted",R.string.AttachStickersRestricted,LocaleController.formatDateForBan(chat.banned_rights.until_date)));
      }
    }
  }
  mediaBanTooltip.setVisibility(View.VISIBLE);
  AnimatorSet AnimatorSet=new AnimatorSet();
  AnimatorSet.playTogether(ObjectAnimator.ofFloat(mediaBanTooltip,View.ALPHA,0.0f,1.0f));
  AnimatorSet.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      AndroidUtilities.runOnUIThread(() -> {
        if (mediaBanTooltip == null) {
          return;
        }
        AnimatorSet AnimatorSet1=new AnimatorSet();
        AnimatorSet1.playTogether(ObjectAnimator.ofFloat(mediaBanTooltip,View.ALPHA,0.0f));
        AnimatorSet1.addListener(new AnimatorListenerAdapter(){
          @Override public void onAnimationEnd(          Animator animation1){
            if (mediaBanTooltip != null) {
              mediaBanTooltip.setVisibility(View.INVISIBLE);
            }
          }
        }
);
        AnimatorSet1.setDuration(300);
        AnimatorSet1.start();
      }
,5000);
    }
  }
);
  AnimatorSet.setDuration(300);
  AnimatorSet.start();
}

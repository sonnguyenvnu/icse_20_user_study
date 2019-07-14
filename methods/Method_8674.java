private void checkLiveLocation(boolean create){
  View fragmentView=fragment.getFragmentView();
  if (!create && fragmentView != null) {
    if (fragmentView.getParent() == null || ((View)fragmentView.getParent()).getVisibility() != VISIBLE) {
      create=true;
    }
  }
  boolean show;
  if (fragment instanceof DialogsActivity) {
    show=LocationController.getLocationsCount() != 0;
  }
 else {
    show=LocationController.getInstance(fragment.getCurrentAccount()).isSharingLocation(((ChatActivity)fragment).getDialogId());
  }
  if (!show) {
    lastLocationSharingCount=-1;
    AndroidUtilities.cancelRunOnUIThread(checkLocationRunnable);
    if (visible) {
      visible=false;
      if (create) {
        if (getVisibility() != GONE) {
          setVisibility(GONE);
        }
        setTopPadding(0);
      }
 else {
        if (animatorSet != null) {
          animatorSet.cancel();
          animatorSet=null;
        }
        animatorSet=new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(this,"topPadding",0));
        animatorSet.setDuration(200);
        animatorSet.addListener(new AnimatorListenerAdapter(){
          @Override public void onAnimationEnd(          Animator animation){
            if (animatorSet != null && animatorSet.equals(animation)) {
              setVisibility(GONE);
              animatorSet=null;
            }
          }
        }
);
        animatorSet.start();
      }
    }
  }
 else {
    updateStyle(2);
    playButton.setImageDrawable(new ShareLocationDrawable(getContext(),true));
    if (create && topPadding == 0) {
      setTopPadding(AndroidUtilities.dp2(36));
      yPosition=0;
    }
    if (!visible) {
      if (!create) {
        if (animatorSet != null) {
          animatorSet.cancel();
          animatorSet=null;
        }
        animatorSet=new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(this,"topPadding",AndroidUtilities.dp2(36)));
        animatorSet.setDuration(200);
        animatorSet.addListener(new AnimatorListenerAdapter(){
          @Override public void onAnimationEnd(          Animator animation){
            if (animatorSet != null && animatorSet.equals(animation)) {
              animatorSet=null;
            }
          }
        }
);
        animatorSet.start();
      }
      visible=true;
      setVisibility(VISIBLE);
    }
    if (fragment instanceof DialogsActivity) {
      String liveLocation=LocaleController.getString("AttachLiveLocation",R.string.AttachLiveLocation);
      String param;
      ArrayList<LocationController.SharingLocationInfo> infos=new ArrayList<>();
      for (int a=0; a < UserConfig.MAX_ACCOUNT_COUNT; a++) {
        infos.addAll(LocationController.getInstance(a).sharingLocationsUI);
      }
      if (infos.size() == 1) {
        LocationController.SharingLocationInfo info=infos.get(0);
        int lower_id=(int)info.messageObject.getDialogId();
        if (lower_id > 0) {
          TLRPC.User user=MessagesController.getInstance(info.messageObject.currentAccount).getUser(lower_id);
          param=UserObject.getFirstName(user);
        }
 else {
          TLRPC.Chat chat=MessagesController.getInstance(info.messageObject.currentAccount).getChat(-lower_id);
          if (chat != null) {
            param=chat.title;
          }
 else {
            param="";
          }
        }
      }
 else {
        param=LocaleController.formatPluralString("Chats",infos.size());
      }
      String fullString=String.format(LocaleController.getString("AttachLiveLocationIsSharing",R.string.AttachLiveLocationIsSharing),liveLocation,param);
      int start=fullString.indexOf(liveLocation);
      SpannableStringBuilder stringBuilder=new SpannableStringBuilder(fullString);
      titleTextView.setEllipsize(TextUtils.TruncateAt.END);
      TypefaceSpan span=new TypefaceSpan(AndroidUtilities.getTypeface("fonts/rmedium.ttf"),0,Theme.getColor(Theme.key_inappPlayerPerformer));
      stringBuilder.setSpan(span,start,start + liveLocation.length(),Spanned.SPAN_INCLUSIVE_INCLUSIVE);
      titleTextView.setText(stringBuilder);
    }
 else {
      checkLocationRunnable.run();
      checkLocationString();
    }
  }
}

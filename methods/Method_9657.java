@Override public void onStateChanged(final int state){
  final int prevState=callState;
  callState=state;
  runOnUiThread(new Runnable(){
    @Override public void run(){
      boolean wasFirstStateChange=firstStateChange;
      if (firstStateChange) {
        spkToggle.setChecked(((AudioManager)getSystemService(AUDIO_SERVICE)).isSpeakerphoneOn());
        if (isIncomingWaiting=state == VoIPService.STATE_WAITING_INCOMING) {
          swipeViewsWrap.setVisibility(View.VISIBLE);
          endBtn.setVisibility(View.GONE);
          acceptSwipe.startAnimatingArrows();
          declineSwipe.startAnimatingArrows();
          if (UserConfig.getActivatedAccountsCount() > 1) {
            TLRPC.User self=UserConfig.getInstance(currentAccount).getCurrentUser();
            accountNameText.setText(LocaleController.formatString("VoipAnsweringAsAccount",R.string.VoipAnsweringAsAccount,ContactsController.formatName(self.first_name,self.last_name)));
          }
 else {
            accountNameText.setVisibility(View.GONE);
          }
          getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
          VoIPService svc=VoIPService.getSharedInstance();
          if (svc != null)           svc.startRingtoneAndVibration();
          setTitle(LocaleController.getString("VoipIncoming",R.string.VoipIncoming));
        }
 else {
          swipeViewsWrap.setVisibility(View.GONE);
          acceptBtn.setVisibility(View.GONE);
          declineBtn.setVisibility(View.GONE);
          accountNameText.setVisibility(View.GONE);
          getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        }
        if (state != VoIPService.STATE_ESTABLISHED)         emojiWrap.setVisibility(View.GONE);
        firstStateChange=false;
      }
      if (isIncomingWaiting && state != VoIPService.STATE_WAITING_INCOMING && state != VoIPBaseService.STATE_ENDED && state != VoIPService.STATE_HANGING_UP) {
        isIncomingWaiting=false;
        if (!didAcceptFromHere)         callAccepted();
      }
      if (state == VoIPService.STATE_WAITING_INCOMING) {
        setStateTextAnimated(LocaleController.getString("VoipIncoming",R.string.VoipIncoming),false);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
      }
 else       if (state == VoIPService.STATE_WAIT_INIT || state == VoIPService.STATE_WAIT_INIT_ACK) {
        setStateTextAnimated(LocaleController.getString("VoipConnecting",R.string.VoipConnecting),true);
      }
 else       if (state == VoIPService.STATE_EXCHANGING_KEYS) {
        setStateTextAnimated(LocaleController.getString("VoipExchangingKeys",R.string.VoipExchangingKeys),true);
      }
 else       if (state == VoIPService.STATE_WAITING) {
        setStateTextAnimated(LocaleController.getString("VoipWaiting",R.string.VoipWaiting),true);
      }
 else       if (state == VoIPService.STATE_RINGING) {
        setStateTextAnimated(LocaleController.getString("VoipRinging",R.string.VoipRinging),true);
      }
 else       if (state == VoIPService.STATE_REQUESTING) {
        setStateTextAnimated(LocaleController.getString("VoipRequesting",R.string.VoipRequesting),true);
      }
 else       if (state == VoIPService.STATE_HANGING_UP) {
        setStateTextAnimated(LocaleController.getString("VoipHangingUp",R.string.VoipHangingUp),true);
        endBtnIcon.setAlpha(.5f);
        endBtn.setEnabled(false);
      }
 else       if (state == VoIPBaseService.STATE_ENDED) {
        setStateTextAnimated(LocaleController.getString("VoipCallEnded",R.string.VoipCallEnded),false);
        stateText.postDelayed(new Runnable(){
          @Override public void run(){
            finish();
          }
        }
,200);
      }
 else       if (state == VoIPService.STATE_BUSY) {
        setStateTextAnimated(LocaleController.getString("VoipBusy",R.string.VoipBusy),false);
        showRetry();
      }
 else       if (state == VoIPService.STATE_ESTABLISHED || state == VoIPService.STATE_RECONNECTING) {
        setTitle(null);
        if (!wasFirstStateChange && state == VoIPService.STATE_ESTABLISHED) {
          int count=MessagesController.getGlobalMainSettings().getInt("call_emoji_tooltip_count",0);
          if (count < 3) {
            setEmojiTooltipVisible(true);
            hintTextView.postDelayed(tooltipHider=new Runnable(){
              @Override public void run(){
                tooltipHider=null;
                setEmojiTooltipVisible(false);
              }
            }
,5000);
            MessagesController.getGlobalMainSettings().edit().putInt("call_emoji_tooltip_count",count + 1).commit();
          }
        }
        if (prevState != VoIPService.STATE_ESTABLISHED && prevState != VoIPService.STATE_RECONNECTING) {
          setStateTextAnimated("0:00",false);
          startUpdatingCallDuration();
          updateKeyView();
          if (emojiWrap.getVisibility() != View.VISIBLE) {
            emojiWrap.setVisibility(View.VISIBLE);
            emojiWrap.setAlpha(0f);
            emojiWrap.animate().alpha(1).setDuration(200).setInterpolator(new DecelerateInterpolator()).start();
          }
        }
      }
 else       if (state == VoIPService.STATE_FAILED) {
        setStateTextAnimated(LocaleController.getString("VoipFailed",R.string.VoipFailed),false);
        int lastError=VoIPService.getSharedInstance() != null ? VoIPService.getSharedInstance().getLastError() : VoIPController.ERROR_UNKNOWN;
        if (lastError == VoIPController.ERROR_INCOMPATIBLE) {
          showErrorDialog(AndroidUtilities.replaceTags(LocaleController.formatString("VoipPeerIncompatible",R.string.VoipPeerIncompatible,ContactsController.formatName(user.first_name,user.last_name))));
        }
 else         if (lastError == VoIPController.ERROR_PEER_OUTDATED) {
          showErrorDialog(AndroidUtilities.replaceTags(LocaleController.formatString("VoipPeerOutdated",R.string.VoipPeerOutdated,ContactsController.formatName(user.first_name,user.last_name))));
        }
 else         if (lastError == VoIPController.ERROR_PRIVACY) {
          showErrorDialog(AndroidUtilities.replaceTags(LocaleController.formatString("CallNotAvailable",R.string.CallNotAvailable,ContactsController.formatName(user.first_name,user.last_name))));
        }
 else         if (lastError == VoIPController.ERROR_AUDIO_IO) {
          showErrorDialog("Error initializing audio hardware");
        }
 else         if (lastError == VoIPController.ERROR_LOCALIZED) {
          finish();
        }
 else         if (lastError == VoIPController.ERROR_CONNECTION_SERVICE) {
          showErrorDialog(LocaleController.getString("VoipErrorUnknown",R.string.VoipErrorUnknown));
        }
 else {
          stateText.postDelayed(new Runnable(){
            @Override public void run(){
              finish();
            }
          }
,1000);
        }
      }
      brandingText.invalidate();
    }
  }
);
}

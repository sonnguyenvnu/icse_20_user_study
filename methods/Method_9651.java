private View createContentView(){
  FrameLayout content=new FrameLayout(this){
    private void setNegativeMargins(    Rect insets,    LayoutParams lp){
      lp.topMargin=-insets.top;
      lp.bottomMargin=-insets.bottom;
      lp.leftMargin=-insets.left;
      lp.rightMargin=-insets.right;
    }
    @Override protected boolean fitSystemWindows(    Rect insets){
      setNegativeMargins(insets,(LayoutParams)photoView.getLayoutParams());
      setNegativeMargins(insets,(LayoutParams)blurOverlayView1.getLayoutParams());
      setNegativeMargins(insets,(LayoutParams)blurOverlayView2.getLayoutParams());
      return super.fitSystemWindows(insets);
    }
  }
;
  content.setBackgroundColor(0);
  content.setFitsSystemWindows(true);
  content.setClipToPadding(false);
  BackupImageView photo=new BackupImageView(this){
    @Override protected void onDraw(    Canvas canvas){
      super.onDraw(canvas);
      paint.setColor(0x4C000000);
      canvas.drawRect(0,0,getWidth(),getHeight(),paint);
      topGradient.setBounds(0,0,getWidth(),AndroidUtilities.dp(170));
      topGradient.setAlpha(128);
      topGradient.draw(canvas);
      bottomGradient.setBounds(0,getHeight() - AndroidUtilities.dp(220),getWidth(),getHeight());
      bottomGradient.setAlpha(178);
      bottomGradient.draw(canvas);
    }
  }
;
  content.addView(photoView=photo);
  blurOverlayView1=new ImageView(this);
  blurOverlayView1.setScaleType(ImageView.ScaleType.CENTER_CROP);
  blurOverlayView1.setAlpha(0f);
  content.addView(blurOverlayView1);
  blurOverlayView2=new ImageView(this);
  blurOverlayView2.setScaleType(ImageView.ScaleType.CENTER_CROP);
  blurOverlayView2.setAlpha(0f);
  content.addView(blurOverlayView2);
  TextView branding=new TextView(this);
  branding.setTextColor(0xCCFFFFFF);
  branding.setText(LocaleController.getString("VoipInCallBranding",R.string.VoipInCallBranding));
  Drawable logo=getResources().getDrawable(R.drawable.notification).mutate();
  logo.setAlpha(0xCC);
  logo.setBounds(0,0,AndroidUtilities.dp(15),AndroidUtilities.dp(15));
  signalBarsDrawable=new SignalBarsDrawable();
  signalBarsDrawable.setBounds(0,0,signalBarsDrawable.getIntrinsicWidth(),signalBarsDrawable.getIntrinsicHeight());
  branding.setCompoundDrawables(LocaleController.isRTL ? signalBarsDrawable : logo,null,LocaleController.isRTL ? logo : signalBarsDrawable,null);
  branding.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
  branding.setGravity(LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT);
  branding.setCompoundDrawablePadding(AndroidUtilities.dp(5));
  branding.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
  content.addView(branding,LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT,Gravity.TOP | (LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT),18,18,18,0));
  brandingText=branding;
  TextView name=new TextView(this);
  name.setSingleLine();
  name.setTextColor(0xFFFFFFFF);
  name.setTextSize(TypedValue.COMPLEX_UNIT_DIP,40);
  name.setEllipsize(TextUtils.TruncateAt.END);
  name.setGravity(LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT);
  name.setShadowLayer(AndroidUtilities.dp(3),0,AndroidUtilities.dp(.666666667f),0x4C000000);
  name.setTypeface(Typeface.create("sans-serif-light",Typeface.NORMAL));
  content.addView(nameText=name,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,Gravity.TOP | Gravity.LEFT,16,43,18,0));
  TextView state=new TextView(this);
  state.setTextColor(0xCCFFFFFF);
  state.setSingleLine();
  state.setEllipsize(TextUtils.TruncateAt.END);
  state.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
  state.setShadowLayer(AndroidUtilities.dp(3),0,AndroidUtilities.dp(.666666667f),0x4C000000);
  state.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);
  state.setGravity(LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT);
  content.addView(stateText=state,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,Gravity.TOP | Gravity.LEFT,18,98,18,0));
  durationText=state;
  state=new TextView(this);
  state.setTextColor(0xCCFFFFFF);
  state.setSingleLine();
  state.setEllipsize(TextUtils.TruncateAt.END);
  state.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
  state.setShadowLayer(AndroidUtilities.dp(3),0,AndroidUtilities.dp(.666666667f),0x4C000000);
  state.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);
  state.setGravity(LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT);
  state.setVisibility(View.GONE);
  content.addView(stateText2=state,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,Gravity.TOP | Gravity.LEFT,18,98,18,0));
  ellSpans=new TextAlphaSpan[]{new TextAlphaSpan(),new TextAlphaSpan(),new TextAlphaSpan()};
  LinearLayout buttons=new LinearLayout(this);
  buttons.setOrientation(LinearLayout.HORIZONTAL);
  content.addView(buttons,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,Gravity.BOTTOM));
  FrameLayout wrap;
  TextView accountName=new TextView(this);
  accountName.setTextColor(0xCCFFFFFF);
  accountName.setSingleLine();
  accountName.setEllipsize(TextUtils.TruncateAt.END);
  accountName.setShadowLayer(AndroidUtilities.dp(3),0,AndroidUtilities.dp(.6666667f),0x4c000000);
  accountName.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);
  accountName.setGravity(LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT);
  content.addView(accountNameText=accountName,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,Gravity.TOP | Gravity.LEFT,18,120,18,0));
  CheckableImageView mic=new CheckableImageView(this);
  mic.setBackgroundResource(R.drawable.bg_voip_icon_btn);
  Drawable micIcon=getResources().getDrawable(R.drawable.ic_mic_off_white_24dp).mutate();
  mic.setAlpha(204);
  mic.setImageDrawable(micIcon);
  mic.setScaleType(ImageView.ScaleType.CENTER);
  mic.setContentDescription(LocaleController.getString("AccDescrMuteMic",R.string.AccDescrMuteMic));
  wrap=new FrameLayout(this);
  wrap.addView(micToggle=mic,LayoutHelper.createFrame(38,38,Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM,0,0,0,10));
  buttons.addView(wrap,LayoutHelper.createLinear(0,LayoutHelper.WRAP_CONTENT,1f));
  ImageView chat=new ImageView(this);
  Drawable chatIcon=getResources().getDrawable(R.drawable.ic_chat_bubble_white_24dp).mutate();
  chatIcon.setAlpha(204);
  chat.setImageDrawable(chatIcon);
  chat.setScaleType(ImageView.ScaleType.CENTER);
  chat.setContentDescription(LocaleController.getString("AccDescrOpenChat",R.string.AccDescrOpenChat));
  wrap=new FrameLayout(this);
  wrap.addView(chatBtn=chat,LayoutHelper.createFrame(38,38,Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM,0,0,0,10));
  buttons.addView(wrap,LayoutHelper.createLinear(0,LayoutHelper.WRAP_CONTENT,1f));
  CheckableImageView speaker=new CheckableImageView(this);
  speaker.setBackgroundResource(R.drawable.bg_voip_icon_btn);
  Drawable speakerIcon=getResources().getDrawable(R.drawable.ic_volume_up_white_24dp).mutate();
  speaker.setAlpha(204);
  speaker.setImageDrawable(speakerIcon);
  speaker.setScaleType(ImageView.ScaleType.CENTER);
  speaker.setContentDescription(LocaleController.getString("VoipAudioRoutingSpeaker",R.string.VoipAudioRoutingSpeaker));
  wrap=new FrameLayout(this);
  wrap.addView(spkToggle=speaker,LayoutHelper.createFrame(38,38,Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM,0,0,0,10));
  buttons.addView(wrap,LayoutHelper.createLinear(0,LayoutHelper.WRAP_CONTENT,1f));
  bottomButtons=buttons;
  LinearLayout swipesWrap=new LinearLayout(this);
  swipesWrap.setOrientation(LinearLayout.HORIZONTAL);
  CallSwipeView acceptSwipe=new CallSwipeView(this);
  acceptSwipe.setColor(0xFF45bc4d);
  acceptSwipe.setContentDescription(LocaleController.getString("Accept",R.string.Accept));
  swipesWrap.addView(this.acceptSwipe=acceptSwipe,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,70,1f,4,4,-35,4));
  CallSwipeView declineSwipe=new CallSwipeView(this);
  declineSwipe.setColor(0xFFe61e44);
  declineSwipe.setContentDescription(LocaleController.getString("Decline",R.string.Decline));
  swipesWrap.addView(this.declineSwipe=declineSwipe,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,70,1f,-35,4,4,4));
  content.addView(swipeViewsWrap=swipesWrap,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,Gravity.BOTTOM,20,0,20,68));
  ImageView acceptBtn=new ImageView(this);
  FabBackgroundDrawable acceptBtnBg=new FabBackgroundDrawable();
  acceptBtnBg.setColor(0xFF45bc4d);
  acceptBtn.setBackgroundDrawable(acceptBtnBg);
  acceptBtn.setImageResource(R.drawable.ic_call_end_white_36dp);
  acceptBtn.setScaleType(ImageView.ScaleType.MATRIX);
  Matrix matrix=new Matrix();
  matrix.setTranslate(AndroidUtilities.dp(17),AndroidUtilities.dp(17));
  matrix.postRotate(-135,AndroidUtilities.dp(35),AndroidUtilities.dp(35));
  acceptBtn.setImageMatrix(matrix);
  content.addView(this.acceptBtn=acceptBtn,LayoutHelper.createFrame(78,78,Gravity.BOTTOM | Gravity.LEFT,20,0,0,68));
  ImageView declineBtn=new ImageView(this);
  FabBackgroundDrawable rejectBtnBg=new FabBackgroundDrawable();
  rejectBtnBg.setColor(0xFFe61e44);
  declineBtn.setBackgroundDrawable(rejectBtnBg);
  declineBtn.setImageResource(R.drawable.ic_call_end_white_36dp);
  declineBtn.setScaleType(ImageView.ScaleType.CENTER);
  content.addView(this.declineBtn=declineBtn,LayoutHelper.createFrame(78,78,Gravity.BOTTOM | Gravity.RIGHT,0,0,20,68));
  acceptSwipe.setViewToDrag(acceptBtn,false);
  declineSwipe.setViewToDrag(declineBtn,true);
  FrameLayout end=new FrameLayout(this);
  FabBackgroundDrawable endBtnBg=new FabBackgroundDrawable();
  endBtnBg.setColor(0xFFe61e44);
  end.setBackgroundDrawable(this.endBtnBg=endBtnBg);
  ImageView endInner=new ImageView(this);
  endInner.setImageResource(R.drawable.ic_call_end_white_36dp);
  endInner.setScaleType(ImageView.ScaleType.CENTER);
  end.addView(endBtnIcon=endInner,LayoutHelper.createFrame(70,70));
  end.setForeground(getResources().getDrawable(R.drawable.fab_highlight_dark));
  end.setContentDescription(LocaleController.getString("VoipEndCall",R.string.VoipEndCall));
  content.addView(endBtn=end,LayoutHelper.createFrame(78,78,Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL,0,0,0,68));
  ImageView cancelBtn=new ImageView(this);
  FabBackgroundDrawable cancelBtnBg=new FabBackgroundDrawable();
  cancelBtnBg.setColor(0xFFFFFFFF);
  cancelBtn.setBackgroundDrawable(cancelBtnBg);
  cancelBtn.setImageResource(R.drawable.edit_cancel);
  cancelBtn.setColorFilter(0x89000000);
  cancelBtn.setScaleType(ImageView.ScaleType.CENTER);
  cancelBtn.setVisibility(View.GONE);
  cancelBtn.setContentDescription(LocaleController.getString("Cancel",R.string.Cancel));
  content.addView(this.cancelBtn=cancelBtn,LayoutHelper.createFrame(78,78,Gravity.BOTTOM | Gravity.LEFT,52,0,0,68));
  emojiWrap=new LinearLayout(this);
  emojiWrap.setOrientation(LinearLayout.HORIZONTAL);
  emojiWrap.setClipToPadding(false);
  emojiWrap.setPivotX(0);
  emojiWrap.setPivotY(0);
  emojiWrap.setPadding(AndroidUtilities.dp(14),AndroidUtilities.dp(10),AndroidUtilities.dp(14),AndroidUtilities.dp(10));
  for (int i=0; i < 4; i++) {
    ImageView emoji=new ImageView(this);
    emoji.setScaleType(ImageView.ScaleType.FIT_XY);
    emojiWrap.addView(emoji,LayoutHelper.createLinear(22,22,i == 0 ? 0 : 4,0,0,0));
    keyEmojiViews[i]=emoji;
  }
  emojiWrap.setOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      if (emojiTooltipVisible) {
        setEmojiTooltipVisible(false);
        if (tooltipHider != null) {
          hintTextView.removeCallbacks(tooltipHider);
          tooltipHider=null;
        }
      }
      setEmojiExpanded(!emojiExpanded);
    }
  }
);
  content.addView(emojiWrap,LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT,Gravity.TOP | (LocaleController.isRTL ? Gravity.LEFT : Gravity.RIGHT)));
  emojiWrap.setOnLongClickListener(new View.OnLongClickListener(){
    @Override public boolean onLongClick(    View v){
      if (emojiExpanded)       return false;
      if (tooltipHider != null) {
        hintTextView.removeCallbacks(tooltipHider);
        tooltipHider=null;
      }
      setEmojiTooltipVisible(!emojiTooltipVisible);
      if (emojiTooltipVisible) {
        hintTextView.postDelayed(tooltipHider=new Runnable(){
          @Override public void run(){
            tooltipHider=null;
            setEmojiTooltipVisible(false);
          }
        }
,5000);
      }
      return true;
    }
  }
);
  emojiExpandedText=new TextView(this);
  emojiExpandedText.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
  emojiExpandedText.setTextColor(0xFFFFFFFF);
  emojiExpandedText.setGravity(Gravity.CENTER);
  emojiExpandedText.setAlpha(0);
  content.addView(emojiExpandedText,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,Gravity.CENTER,10,32,10,0));
  hintTextView=new CorrectlyMeasuringTextView(this);
  hintTextView.setBackgroundDrawable(Theme.createRoundRectDrawable(AndroidUtilities.dp(3),0xf2333333));
  hintTextView.setTextColor(Theme.getColor(Theme.key_chat_gifSaveHintText));
  hintTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
  hintTextView.setPadding(AndroidUtilities.dp(10),AndroidUtilities.dp(10),AndroidUtilities.dp(10),AndroidUtilities.dp(10));
  hintTextView.setGravity(Gravity.CENTER);
  hintTextView.setMaxWidth(AndroidUtilities.dp(300));
  hintTextView.setAlpha(0.0f);
  content.addView(hintTextView,LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT,Gravity.TOP | Gravity.RIGHT,0,42,10,0));
  int ellMaxAlpha=stateText.getPaint().getAlpha();
  ellAnimator=new AnimatorSet();
  ellAnimator.playTogether(createAlphaAnimator(ellSpans[0],0,ellMaxAlpha,0,300),createAlphaAnimator(ellSpans[1],0,ellMaxAlpha,150,300),createAlphaAnimator(ellSpans[2],0,ellMaxAlpha,300,300),createAlphaAnimator(ellSpans[0],ellMaxAlpha,0,1000,400),createAlphaAnimator(ellSpans[1],ellMaxAlpha,0,1000,400),createAlphaAnimator(ellSpans[2],ellMaxAlpha,0,1000,400));
  ellAnimator.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      if (!isFinishing()) {
        VoIPActivity.this.content.postDelayed(restarter,300);
      }
    }
  }
);
  content.setClipChildren(false);
  this.content=content;
  return content;
}

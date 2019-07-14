private void showMessagesSheet(){
  if (VoIPService.getSharedInstance() != null)   VoIPService.getSharedInstance().stopRinging();
  SharedPreferences prefs=getSharedPreferences("mainconfig",MODE_PRIVATE);
  final String[] msgs={prefs.getString("quick_reply_msg1",LocaleController.getString("QuickReplyDefault1",R.string.QuickReplyDefault1)),prefs.getString("quick_reply_msg2",LocaleController.getString("QuickReplyDefault2",R.string.QuickReplyDefault2)),prefs.getString("quick_reply_msg3",LocaleController.getString("QuickReplyDefault3",R.string.QuickReplyDefault3)),prefs.getString("quick_reply_msg4",LocaleController.getString("QuickReplyDefault4",R.string.QuickReplyDefault4))};
  LinearLayout sheetView=new LinearLayout(this);
  sheetView.setOrientation(LinearLayout.VERTICAL);
  final BottomSheet sheet=new BottomSheet(this,true,0);
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    getWindow().setNavigationBarColor(0xff2b2b2b);
    sheet.setOnDismissListener(new DialogInterface.OnDismissListener(){
      @Override public void onDismiss(      DialogInterface dialog){
        getWindow().setNavigationBarColor(0);
      }
    }
);
  }
  View.OnClickListener listener=new View.OnClickListener(){
    @Override public void onClick(    final View v){
      sheet.dismiss();
      if (VoIPService.getSharedInstance() != null)       VoIPService.getSharedInstance().declineIncomingCall(VoIPService.DISCARD_REASON_LINE_BUSY,new Runnable(){
        @Override public void run(){
          sendTextMessage((String)v.getTag());
        }
      }
);
    }
  }
;
  for (  String msg : msgs) {
    BottomSheet.BottomSheetCell cell=new BottomSheet.BottomSheetCell(this,0);
    cell.setTextAndIcon(msg,0);
    cell.setTextColor(0xFFFFFFFF);
    cell.setTag(msg);
    cell.setOnClickListener(listener);
    sheetView.addView(cell);
  }
  FrameLayout customWrap=new FrameLayout(this);
  final BottomSheet.BottomSheetCell cell=new BottomSheet.BottomSheetCell(this,0);
  cell.setTextAndIcon(LocaleController.getString("QuickReplyCustom",R.string.QuickReplyCustom),0);
  cell.setTextColor(0xFFFFFFFF);
  customWrap.addView(cell);
  final FrameLayout editor=new FrameLayout(this);
  final EditText field=new EditText(this);
  field.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
  field.setTextColor(0xFFFFFFFF);
  field.setHintTextColor(DarkTheme.getColor(Theme.key_chat_messagePanelHint));
  field.setBackgroundDrawable(null);
  field.setPadding(AndroidUtilities.dp(16),AndroidUtilities.dp(11),AndroidUtilities.dp(16),AndroidUtilities.dp(12));
  field.setHint(LocaleController.getString("QuickReplyCustom",R.string.QuickReplyCustom));
  field.setMinHeight(AndroidUtilities.dp(48));
  field.setGravity(Gravity.BOTTOM);
  field.setMaxLines(4);
  field.setSingleLine(false);
  field.setInputType(field.getInputType() | EditorInfo.TYPE_TEXT_FLAG_CAP_SENTENCES | EditorInfo.TYPE_TEXT_FLAG_MULTI_LINE);
  editor.addView(field,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,(LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT),LocaleController.isRTL ? 48 : 0,0,LocaleController.isRTL ? 0 : 48,0));
  final ImageView sendBtn=new ImageView(this);
  sendBtn.setScaleType(ImageView.ScaleType.CENTER);
  sendBtn.setImageDrawable(DarkTheme.getThemedDrawable(this,R.drawable.ic_send,Theme.key_chat_messagePanelSend));
  if (LocaleController.isRTL)   sendBtn.setScaleX(-0.1f);
 else   sendBtn.setScaleX(0.1f);
  sendBtn.setScaleY(0.1f);
  sendBtn.setAlpha(0f);
  editor.addView(sendBtn,LayoutHelper.createFrame(48,48,Gravity.BOTTOM | (LocaleController.isRTL ? Gravity.LEFT : Gravity.RIGHT)));
  sendBtn.setOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      if (field.length() == 0)       return;
      sheet.dismiss();
      if (VoIPService.getSharedInstance() != null)       VoIPService.getSharedInstance().declineIncomingCall(VoIPService.DISCARD_REASON_LINE_BUSY,new Runnable(){
        @Override public void run(){
          sendTextMessage(field.getText().toString());
        }
      }
);
    }
  }
);
  sendBtn.setVisibility(View.INVISIBLE);
  final ImageView cancelBtn=new ImageView(this);
  cancelBtn.setScaleType(ImageView.ScaleType.CENTER);
  cancelBtn.setImageDrawable(DarkTheme.getThemedDrawable(this,R.drawable.edit_cancel,Theme.key_chat_messagePanelIcons));
  editor.addView(cancelBtn,LayoutHelper.createFrame(48,48,Gravity.BOTTOM | (LocaleController.isRTL ? Gravity.LEFT : Gravity.RIGHT)));
  cancelBtn.setOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      editor.setVisibility(View.GONE);
      cell.setVisibility(View.VISIBLE);
      field.setText("");
      InputMethodManager imm=(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(field.getWindowToken(),0);
    }
  }
);
  field.addTextChangedListener(new TextWatcher(){
    @Override public void beforeTextChanged(    CharSequence s,    int start,    int count,    int after){
    }
    @Override public void onTextChanged(    CharSequence s,    int start,    int before,    int count){
    }
    @Override public void afterTextChanged(    Editable s){
      boolean hasText=s.length() > 0;
      if (prevState != hasText) {
        prevState=hasText;
        if (hasText) {
          sendBtn.setVisibility(View.VISIBLE);
          sendBtn.animate().alpha(1).scaleX(LocaleController.isRTL ? -1 : 1).scaleY(1).setDuration(200).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
          cancelBtn.animate().alpha(0).scaleX(0.1f).scaleY(0.1f).setInterpolator(CubicBezierInterpolator.DEFAULT).setDuration(200).withEndAction(new Runnable(){
            @Override public void run(){
              cancelBtn.setVisibility(View.INVISIBLE);
            }
          }
).start();
        }
 else {
          cancelBtn.setVisibility(View.VISIBLE);
          cancelBtn.animate().alpha(1).scaleX(1).scaleY(1).setDuration(200).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
          sendBtn.animate().alpha(0).scaleX(LocaleController.isRTL ? -0.1f : 0.1f).scaleY(0.1f).setInterpolator(CubicBezierInterpolator.DEFAULT).setDuration(200).withEndAction(new Runnable(){
            @Override public void run(){
              sendBtn.setVisibility(View.INVISIBLE);
            }
          }
).start();
        }
      }
    }
  }
);
  editor.setVisibility(View.GONE);
  customWrap.addView(editor);
  cell.setOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      editor.setVisibility(View.VISIBLE);
      cell.setVisibility(View.INVISIBLE);
      field.requestFocus();
      InputMethodManager imm=(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
      imm.showSoftInput(field,0);
    }
  }
);
  sheetView.addView(customWrap);
  sheet.setCustomView(sheetView);
  sheet.setBackgroundColor(0xff2b2b2b);
  sheet.show();
}

private LinearLayout getButtonsViewForMessage(int num,boolean applyOffset){
  if (popupMessages.size() == 1 && (num < 0 || num >= popupMessages.size())) {
    return null;
  }
  if (num == -1) {
    num=popupMessages.size() - 1;
  }
 else   if (num == popupMessages.size()) {
    num=0;
  }
  LinearLayout view=null;
  final MessageObject messageObject=popupMessages.get(num);
  int buttonsCount=0;
  TLRPC.ReplyMarkup markup=messageObject.messageOwner.reply_markup;
  if (messageObject.getDialogId() == 777000 && markup != null) {
    ArrayList<TLRPC.TL_keyboardButtonRow> rows=markup.rows;
    for (int a=0, size=rows.size(); a < size; a++) {
      TLRPC.TL_keyboardButtonRow row=rows.get(a);
      for (int b=0, size2=row.buttons.size(); b < size2; b++) {
        TLRPC.KeyboardButton button=row.buttons.get(b);
        if (button instanceof TLRPC.TL_keyboardButtonCallback) {
          buttonsCount++;
        }
      }
    }
  }
  final int account=messageObject.currentAccount;
  if (buttonsCount > 0) {
    ArrayList<TLRPC.TL_keyboardButtonRow> rows=markup.rows;
    for (int a=0, size=rows.size(); a < size; a++) {
      TLRPC.TL_keyboardButtonRow row=rows.get(a);
      for (int b=0, size2=row.buttons.size(); b < size2; b++) {
        TLRPC.KeyboardButton button=row.buttons.get(b);
        if (button instanceof TLRPC.TL_keyboardButtonCallback) {
          if (view == null) {
            view=new LinearLayout(this);
            view.setOrientation(LinearLayout.HORIZONTAL);
            view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            view.setWeightSum(100);
            view.setTag("b");
            view.setOnTouchListener((v,event) -> true);
          }
          TextView textView=new TextView(this);
          textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
          textView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText));
          textView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
          textView.setText(button.text.toUpperCase());
          textView.setTag(button);
          textView.setGravity(Gravity.CENTER);
          textView.setBackgroundDrawable(Theme.getSelectorDrawable(true));
          view.addView(textView,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT,100.0f / buttonsCount));
          textView.setOnClickListener(v -> {
            TLRPC.KeyboardButton button1=(TLRPC.KeyboardButton)v.getTag();
            if (button1 != null) {
              SendMessagesHelper.getInstance(account).sendNotificationCallback(messageObject.getDialogId(),messageObject.getId(),button1.data);
            }
          }
);
        }
      }
    }
  }
  if (view != null) {
    int widht=AndroidUtilities.displaySize.x - AndroidUtilities.dp(24);
    RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
    if (applyOffset) {
      if (num == currentMessageNum) {
        view.setTranslationX(0);
      }
 else       if (num == currentMessageNum - 1) {
        view.setTranslationX(-widht);
      }
 else       if (num == currentMessageNum + 1) {
        view.setTranslationX(widht);
      }
    }
    popupContainer.addView(view,layoutParams);
  }
  return view;
}

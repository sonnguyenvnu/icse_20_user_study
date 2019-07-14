private void didSelectResult(final TLRPC.User user,boolean useAlert,String param){
  if (useAlert && selectAlertString != null) {
    if (getParentActivity() == null) {
      return;
    }
    if (user.bot) {
      if (user.bot_nochats) {
        try {
          Toast.makeText(getParentActivity(),LocaleController.getString("BotCantJoinGroups",R.string.BotCantJoinGroups),Toast.LENGTH_SHORT).show();
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
        return;
      }
      if (channelId != 0) {
        TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(channelId);
        AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
        if (ChatObject.canAddAdmins(chat)) {
          builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
          builder.setMessage(LocaleController.getString("AddBotAsAdmin",R.string.AddBotAsAdmin));
          builder.setPositiveButton(LocaleController.getString("MakeAdmin",R.string.MakeAdmin),(dialogInterface,i) -> {
            if (delegate != null) {
              delegate.didSelectContact(user,param,this);
              delegate=null;
            }
          }
);
          builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
        }
 else {
          builder.setMessage(LocaleController.getString("CantAddBotAsAdmin",R.string.CantAddBotAsAdmin));
          builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
        }
        showDialog(builder.create());
        return;
      }
    }
    AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
    builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
    String message=LocaleController.formatStringSimple(selectAlertString,UserObject.getUserName(user));
    EditText editText=null;
    if (!user.bot && needForwardCount) {
      message=String.format("%s\n\n%s",message,LocaleController.getString("AddToTheGroupForwardCount",R.string.AddToTheGroupForwardCount));
      editText=new EditText(getParentActivity());
      editText.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);
      editText.setText("50");
      editText.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
      editText.setGravity(Gravity.CENTER);
      editText.setInputType(InputType.TYPE_CLASS_NUMBER);
      editText.setImeOptions(EditorInfo.IME_ACTION_DONE);
      editText.setBackgroundDrawable(Theme.createEditTextDrawable(getParentActivity(),true));
      final EditText editTextFinal=editText;
      editText.addTextChangedListener(new TextWatcher(){
        @Override public void beforeTextChanged(        CharSequence s,        int start,        int count,        int after){
        }
        @Override public void onTextChanged(        CharSequence s,        int start,        int before,        int count){
        }
        @Override public void afterTextChanged(        Editable s){
          try {
            String str=s.toString();
            if (str.length() != 0) {
              int value=Utilities.parseInt(str);
              if (value < 0) {
                editTextFinal.setText("0");
                editTextFinal.setSelection(editTextFinal.length());
              }
 else               if (value > 300) {
                editTextFinal.setText("300");
                editTextFinal.setSelection(editTextFinal.length());
              }
 else               if (!str.equals("" + value)) {
                editTextFinal.setText("" + value);
                editTextFinal.setSelection(editTextFinal.length());
              }
            }
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
        }
      }
);
      builder.setView(editText);
    }
    builder.setMessage(message);
    final EditText finalEditText=editText;
    builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),(dialogInterface,i) -> didSelectResult(user,false,finalEditText != null ? finalEditText.getText().toString() : "0"));
    builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
    showDialog(builder.create());
    if (editText != null) {
      ViewGroup.MarginLayoutParams layoutParams=(ViewGroup.MarginLayoutParams)editText.getLayoutParams();
      if (layoutParams != null) {
        if (layoutParams instanceof FrameLayout.LayoutParams) {
          ((FrameLayout.LayoutParams)layoutParams).gravity=Gravity.CENTER_HORIZONTAL;
        }
        layoutParams.rightMargin=layoutParams.leftMargin=AndroidUtilities.dp(24);
        layoutParams.height=AndroidUtilities.dp(36);
        editText.setLayoutParams(layoutParams);
      }
      editText.setSelection(editText.getText().length());
    }
  }
 else {
    if (delegate != null) {
      delegate.didSelectContact(user,param,this);
      delegate=null;
    }
    if (needFinishFragment) {
      finishFragment();
    }
  }
}

private void openThemeCreate(){
  final EditTextBoldCursor editText=new EditTextBoldCursor(getParentActivity());
  editText.setBackgroundDrawable(Theme.createEditTextDrawable(getParentActivity(),true));
  AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
  builder.setTitle(LocaleController.getString("NewTheme",R.string.NewTheme));
  builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
  builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),(dialog,which) -> {
  }
);
  LinearLayout linearLayout=new LinearLayout(getParentActivity());
  linearLayout.setOrientation(LinearLayout.VERTICAL);
  builder.setView(linearLayout);
  final TextView message=new TextView(getParentActivity());
  message.setText(LocaleController.formatString("EnterThemeName",R.string.EnterThemeName));
  message.setTextSize(16);
  message.setPadding(AndroidUtilities.dp(23),AndroidUtilities.dp(12),AndroidUtilities.dp(23),AndroidUtilities.dp(6));
  message.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
  linearLayout.addView(message,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
  editText.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
  editText.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
  editText.setMaxLines(1);
  editText.setLines(1);
  editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
  editText.setGravity(Gravity.LEFT | Gravity.TOP);
  editText.setSingleLine(true);
  editText.setImeOptions(EditorInfo.IME_ACTION_DONE);
  editText.setCursorColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
  editText.setCursorSize(AndroidUtilities.dp(20));
  editText.setCursorWidth(1.5f);
  editText.setPadding(0,AndroidUtilities.dp(4),0,0);
  linearLayout.addView(editText,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,36,Gravity.TOP | Gravity.LEFT,24,6,24,0));
  editText.setOnEditorActionListener((textView,i,keyEvent) -> {
    AndroidUtilities.hideKeyboard(textView);
    return false;
  }
);
  final AlertDialog alertDialog=builder.create();
  alertDialog.setOnShowListener(dialog -> AndroidUtilities.runOnUIThread(() -> {
    editText.requestFocus();
    AndroidUtilities.showKeyboard(editText);
  }
));
  showDialog(alertDialog);
  alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
    if (editText.length() == 0) {
      Vibrator vibrator=(Vibrator)ApplicationLoader.applicationContext.getSystemService(Context.VIBRATOR_SERVICE);
      if (vibrator != null) {
        vibrator.vibrate(200);
      }
      AndroidUtilities.shakeView(editText,2,0);
      return;
    }
    ThemeEditorView themeEditorView=new ThemeEditorView();
    String name=editText.getText().toString() + ".attheme";
    themeEditorView.show(getParentActivity(),name);
    Theme.saveCurrentTheme(name,true);
    updateRows();
    alertDialog.dismiss();
    SharedPreferences preferences=MessagesController.getGlobalMainSettings();
    if (preferences.getBoolean("themehint",false)) {
      return;
    }
    preferences.edit().putBoolean("themehint",true).commit();
    try {
      Toast.makeText(getParentActivity(),LocaleController.getString("CreateNewThemeHelp",R.string.CreateNewThemeHelp),Toast.LENGTH_LONG).show();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}

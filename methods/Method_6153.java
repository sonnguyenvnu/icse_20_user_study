public static void showProxyAlert(Activity activity,final String address,final String port,final String user,final String password,final String secret){
  BottomSheet.Builder builder=new BottomSheet.Builder(activity);
  final Runnable dismissRunnable=builder.getDismissRunnable();
  builder.setApplyTopPadding(false);
  builder.setApplyBottomPadding(false);
  LinearLayout linearLayout=new LinearLayout(activity);
  builder.setCustomView(linearLayout);
  linearLayout.setOrientation(LinearLayout.VERTICAL);
  if (!TextUtils.isEmpty(secret)) {
    TextView titleTextView=new TextView(activity);
    titleTextView.setText(LocaleController.getString("UseProxyTelegramInfo2",R.string.UseProxyTelegramInfo2));
    titleTextView.setTextColor(Theme.getColor(Theme.key_dialogTextGray4));
    titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
    titleTextView.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
    linearLayout.addView(titleTextView,LayoutHelper.createLinear(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT,(LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.TOP,17,8,17,8));
    View lineView=new View(activity);
    lineView.setBackgroundColor(Theme.getColor(Theme.key_divider));
    linearLayout.addView(lineView,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,1));
  }
  for (int a=0; a < 5; a++) {
    String text=null;
    String detail=null;
    if (a == 0) {
      text=address;
      detail=LocaleController.getString("UseProxyAddress",R.string.UseProxyAddress);
    }
 else     if (a == 1) {
      text="" + port;
      detail=LocaleController.getString("UseProxyPort",R.string.UseProxyPort);
    }
 else     if (a == 2) {
      text=secret;
      detail=LocaleController.getString("UseProxySecret",R.string.UseProxySecret);
    }
 else     if (a == 3) {
      text=user;
      detail=LocaleController.getString("UseProxyUsername",R.string.UseProxyUsername);
    }
 else     if (a == 4) {
      text=password;
      detail=LocaleController.getString("UseProxyPassword",R.string.UseProxyPassword);
    }
    if (TextUtils.isEmpty(text)) {
      continue;
    }
    TextDetailSettingsCell cell=new TextDetailSettingsCell(activity);
    cell.setTextAndValue(text,detail,true);
    cell.getTextView().setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
    cell.getValueTextView().setTextColor(Theme.getColor(Theme.key_dialogTextGray3));
    linearLayout.addView(cell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
    if (a == 2) {
      break;
    }
  }
  PickerBottomLayout pickerBottomLayout=new PickerBottomLayout(activity,false);
  pickerBottomLayout.setBackgroundColor(Theme.getColor(Theme.key_dialogBackground));
  linearLayout.addView(pickerBottomLayout,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,48,Gravity.LEFT | Gravity.BOTTOM));
  pickerBottomLayout.cancelButton.setPadding(AndroidUtilities.dp(18),0,AndroidUtilities.dp(18),0);
  pickerBottomLayout.cancelButton.setTextColor(Theme.getColor(Theme.key_dialogTextBlue2));
  pickerBottomLayout.cancelButton.setText(LocaleController.getString("Cancel",R.string.Cancel).toUpperCase());
  pickerBottomLayout.cancelButton.setOnClickListener(view -> dismissRunnable.run());
  pickerBottomLayout.doneButtonTextView.setTextColor(Theme.getColor(Theme.key_dialogTextBlue2));
  pickerBottomLayout.doneButton.setPadding(AndroidUtilities.dp(18),0,AndroidUtilities.dp(18),0);
  pickerBottomLayout.doneButtonBadgeTextView.setVisibility(View.GONE);
  pickerBottomLayout.doneButtonTextView.setText(LocaleController.getString("ConnectingConnectProxy",R.string.ConnectingConnectProxy).toUpperCase());
  pickerBottomLayout.doneButton.setOnClickListener(v -> {
    SharedPreferences.Editor editor=MessagesController.getGlobalMainSettings().edit();
    editor.putBoolean("proxy_enabled",true);
    editor.putString("proxy_ip",address);
    int p=Utilities.parseInt(port);
    editor.putInt("proxy_port",p);
    SharedConfig.ProxyInfo info;
    if (TextUtils.isEmpty(secret)) {
      editor.remove("proxy_secret");
      if (TextUtils.isEmpty(password)) {
        editor.remove("proxy_pass");
      }
 else {
        editor.putString("proxy_pass",password);
      }
      if (TextUtils.isEmpty(user)) {
        editor.remove("proxy_user");
      }
 else {
        editor.putString("proxy_user",user);
      }
      info=new SharedConfig.ProxyInfo(address,p,user,password,"");
    }
 else {
      editor.remove("proxy_pass");
      editor.remove("proxy_user");
      editor.putString("proxy_secret",secret);
      info=new SharedConfig.ProxyInfo(address,p,"","",secret);
    }
    editor.commit();
    SharedConfig.currentProxy=SharedConfig.addProxy(info);
    ConnectionsManager.setProxySettings(true,address,p,user,password,secret);
    NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.proxySettingsChanged);
    dismissRunnable.run();
  }
);
  builder.show();
}

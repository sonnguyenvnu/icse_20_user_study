@TargetApi(Build.VERSION_CODES.O) protected PhoneAccountHandle addAccountToTelecomManager(){
  TelecomManager tm=(TelecomManager)getSystemService(TELECOM_SERVICE);
  TLRPC.User self=UserConfig.getInstance(currentAccount).getCurrentUser();
  PhoneAccountHandle handle=new PhoneAccountHandle(new ComponentName(this,TelegramConnectionService.class),"" + self.id);
  PhoneAccount account=new PhoneAccount.Builder(handle,ContactsController.formatName(self.first_name,self.last_name)).setCapabilities(PhoneAccount.CAPABILITY_SELF_MANAGED).setIcon(Icon.createWithResource(this,R.drawable.ic_launcher_dr)).setHighlightColor(0xff2ca5e0).addSupportedUriScheme("sip").build();
  tm.registerPhoneAccount(account);
  return handle;
}

private void startPhoneVerification(boolean checkPermissions,final String phone,Runnable finishRunnable,ErrorRunnable errorRunnable,final PassportActivityDelegate delegate){
  TelephonyManager tm=(TelephonyManager)ApplicationLoader.applicationContext.getSystemService(Context.TELEPHONY_SERVICE);
  boolean simcardAvailable=tm.getSimState() != TelephonyManager.SIM_STATE_ABSENT && tm.getPhoneType() != TelephonyManager.PHONE_TYPE_NONE;
  boolean allowCall=true;
  if (getParentActivity() != null && Build.VERSION.SDK_INT >= 23 && simcardAvailable) {
    allowCall=getParentActivity().checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED;
    if (checkPermissions) {
      permissionsItems.clear();
      if (!allowCall) {
        permissionsItems.add(Manifest.permission.READ_PHONE_STATE);
      }
      if (!permissionsItems.isEmpty()) {
        if (getParentActivity().shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE)) {
          AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
          builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
          builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
          builder.setMessage(LocaleController.getString("AllowReadCall",R.string.AllowReadCall));
          permissionsDialog=showDialog(builder.create());
        }
 else {
          getParentActivity().requestPermissions(permissionsItems.toArray(new String[0]),6);
        }
        pendingPhone=phone;
        pendingErrorRunnable=errorRunnable;
        pendingFinishRunnable=finishRunnable;
        pendingDelegate=delegate;
        return;
      }
    }
  }
  final TLRPC.TL_account_sendVerifyPhoneCode req=new TLRPC.TL_account_sendVerifyPhoneCode();
  req.phone_number=phone;
  req.settings=new TLRPC.TL_codeSettings();
  req.settings.allow_flashcall=simcardAvailable && allowCall;
  if (Build.VERSION.SDK_INT >= 26) {
    try {
      req.settings.app_hash=SmsManager.getDefault().createAppSpecificSmsToken(PendingIntent.getBroadcast(ApplicationLoader.applicationContext,0,new Intent(ApplicationLoader.applicationContext,SmsReceiver.class),PendingIntent.FLAG_UPDATE_CURRENT));
    }
 catch (    Throwable e) {
      FileLog.e(e);
    }
  }
 else {
    req.settings.app_hash=BuildVars.SMS_HASH;
    req.settings.app_hash_persistent=true;
  }
  SharedPreferences preferences=ApplicationLoader.applicationContext.getSharedPreferences("mainconfig",Activity.MODE_PRIVATE);
  if (!TextUtils.isEmpty(req.settings.app_hash)) {
    req.settings.flags|=8;
    preferences.edit().putString("sms_hash",req.settings.app_hash).commit();
  }
 else {
    preferences.edit().remove("sms_hash").commit();
  }
  if (req.settings.allow_flashcall) {
    try {
      @SuppressLint("HardwareIds") String number=tm.getLine1Number();
      if (!TextUtils.isEmpty(number)) {
        req.settings.current_number=PhoneNumberUtils.compare(phone,number);
        if (!req.settings.current_number) {
          req.settings.allow_flashcall=false;
        }
      }
 else {
        req.settings.current_number=false;
      }
    }
 catch (    Exception e) {
      req.settings.allow_flashcall=false;
      FileLog.e(e);
    }
  }
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    if (error == null) {
      HashMap<String,String> values=new HashMap<>();
      values.put("phone",phone);
      PassportActivity activity=new PassportActivity(TYPE_PHONE_VERIFICATION,currentForm,currentPassword,currentType,null,null,null,values,null);
      activity.currentAccount=currentAccount;
      activity.saltedPassword=saltedPassword;
      activity.secureSecret=secureSecret;
      activity.delegate=delegate;
      activity.currentPhoneVerification=(TLRPC.TL_auth_sentCode)response;
      presentFragment(activity,true);
    }
 else {
      AlertsCreator.processError(currentAccount,error,PassportActivity.this,req,phone);
    }
  }
),ConnectionsManager.RequestFlagFailOnServerErrors);
}

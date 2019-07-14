@SuppressLint("HardwareIds") public void fillNumber(String number){
  try {
    TelephonyManager tm=(TelephonyManager)ApplicationLoader.applicationContext.getSystemService(Context.TELEPHONY_SERVICE);
    boolean allowCall=true;
    boolean allowSms=true;
    if (number != null || tm.getSimState() != TelephonyManager.SIM_STATE_ABSENT && tm.getPhoneType() != TelephonyManager.PHONE_TYPE_NONE) {
      if (Build.VERSION.SDK_INT >= 23) {
        allowCall=getParentActivity().checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED;
      }
      if (number != null || allowCall) {
        if (number == null) {
          number=PhoneFormat.stripExceptNumbers(tm.getLine1Number());
        }
        String textToSet=null;
        boolean ok=false;
        if (!TextUtils.isEmpty(number)) {
          if (number.length() > 4) {
            for (int a=4; a >= 1; a--) {
              String sub=number.substring(0,a);
              String country=codesMap.get(sub);
              if (country != null) {
                ok=true;
                textToSet=number.substring(a);
                inputFields[FIELD_PHONECODE].setText(sub);
                break;
              }
            }
            if (!ok) {
              textToSet=number.substring(1);
              inputFields[FIELD_PHONECODE].setText(number.substring(0,1));
            }
          }
          if (textToSet != null) {
            inputFields[FIELD_PHONE].setText(textToSet);
            inputFields[FIELD_PHONE].setSelection(inputFields[FIELD_PHONE].length());
          }
        }
      }
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}

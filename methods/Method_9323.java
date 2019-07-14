private void needShowInvalidAlert(final String phoneNumber,final boolean banned){
  if (getParentActivity() == null) {
    return;
  }
  AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
  builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
  if (banned) {
    builder.setMessage(LocaleController.getString("BannedPhoneNumber",R.string.BannedPhoneNumber));
  }
 else {
    builder.setMessage(LocaleController.getString("InvalidPhoneNumber",R.string.InvalidPhoneNumber));
  }
  builder.setNeutralButton(LocaleController.getString("BotHelp",R.string.BotHelp),(dialog,which) -> {
    try {
      PackageInfo pInfo=ApplicationLoader.applicationContext.getPackageManager().getPackageInfo(ApplicationLoader.applicationContext.getPackageName(),0);
      String version=String.format(Locale.US,"%s (%d)",pInfo.versionName,pInfo.versionCode);
      Intent mailer=new Intent(Intent.ACTION_SEND);
      mailer.setType("message/rfc822");
      mailer.putExtra(Intent.EXTRA_EMAIL,new String[]{"login@stel.com"});
      if (banned) {
        mailer.putExtra(Intent.EXTRA_SUBJECT,"Banned phone number: " + phoneNumber);
        mailer.putExtra(Intent.EXTRA_TEXT,"I'm trying to use my mobile phone number: " + phoneNumber + "\nBut Telegram says it's banned. Please help.\n\nApp version: " + version + "\nOS version: SDK " + Build.VERSION.SDK_INT + "\nDevice Name: " + Build.MANUFACTURER + Build.MODEL + "\nLocale: " + Locale.getDefault());
      }
 else {
        mailer.putExtra(Intent.EXTRA_SUBJECT,"Invalid phone number: " + phoneNumber);
        mailer.putExtra(Intent.EXTRA_TEXT,"I'm trying to use my mobile phone number: " + phoneNumber + "\nBut Telegram says it's invalid. Please help.\n\nApp version: " + version + "\nOS version: SDK " + Build.VERSION.SDK_INT + "\nDevice Name: " + Build.MANUFACTURER + Build.MODEL + "\nLocale: " + Locale.getDefault());
      }
      getParentActivity().startActivity(Intent.createChooser(mailer,"Send email..."));
    }
 catch (    Exception e) {
      needShowAlert(LocaleController.getString("AppName",R.string.AppName),LocaleController.getString("NoMailInstalled",R.string.NoMailInstalled));
    }
  }
);
  builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
  showDialog(builder.create());
}

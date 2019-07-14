public static AlertDialog.Builder createSimpleAlert(Context context,final String text){
  if (text == null) {
    return null;
  }
  AlertDialog.Builder builder=new AlertDialog.Builder(context);
  builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
  builder.setMessage(text);
  builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
  return builder;
}

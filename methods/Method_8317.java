private void alertUserOpenError(MessageObject message){
  if (getParentActivity() == null) {
    return;
  }
  AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
  builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
  builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
  if (message.type == 3) {
    builder.setMessage(LocaleController.getString("NoPlayerInstalled",R.string.NoPlayerInstalled));
  }
 else {
    builder.setMessage(LocaleController.formatString("NoHandleAppInstalled",R.string.NoHandleAppInstalled,message.getDocument().mime_type));
  }
  showDialog(builder.create());
}

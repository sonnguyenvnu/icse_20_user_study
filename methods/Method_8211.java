private void showErrorAlert(String error){
  if (getParentActivity() == null) {
    return;
  }
  AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
  builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
switch (error) {
case "USERNAME_INVALID":
    builder.setMessage(LocaleController.getString("LinkInvalid",R.string.LinkInvalid));
  break;
case "USERNAME_OCCUPIED":
builder.setMessage(LocaleController.getString("LinkInUse",R.string.LinkInUse));
break;
default :
builder.setMessage(LocaleController.getString("ErrorOccurred",R.string.ErrorOccurred));
break;
}
builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
showDialog(builder.create());
}

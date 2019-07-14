private void showErrorBox(String error){
  if (getParentActivity() == null) {
    return;
  }
  new AlertDialog.Builder(getParentActivity()).setTitle(LocaleController.getString("AppName",R.string.AppName)).setMessage(error).setPositiveButton(LocaleController.getString("OK",R.string.OK),null).show();
}

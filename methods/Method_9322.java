private void needShowAlert(String title,String text){
  if (text == null || getParentActivity() == null) {
    return;
  }
  AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
  builder.setTitle(title);
  builder.setMessage(text);
  builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
  showDialog(builder.create());
}

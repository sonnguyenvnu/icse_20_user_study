private void showAlertWithText(String title,String text){
  AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
  builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
  builder.setTitle(title);
  builder.setMessage(text);
  showDialog(builder.create());
}

private void updateUiForType(){
  if (currentType == 0) {
    bottomCell.setText(LocaleController.getString("UseProxyInfo",R.string.UseProxyInfo));
    ((View)inputFields[FIELD_SECRET].getParent()).setVisibility(View.GONE);
    ((View)inputFields[FIELD_PASSWORD].getParent()).setVisibility(View.VISIBLE);
    ((View)inputFields[FIELD_USER].getParent()).setVisibility(View.VISIBLE);
  }
 else   if (currentType == 1) {
    bottomCell.setText(LocaleController.getString("UseProxyTelegramInfo",R.string.UseProxyTelegramInfo) + "\n\n" + LocaleController.getString("UseProxyTelegramInfo2",R.string.UseProxyTelegramInfo2));
    ((View)inputFields[FIELD_SECRET].getParent()).setVisibility(View.VISIBLE);
    ((View)inputFields[FIELD_PASSWORD].getParent()).setVisibility(View.GONE);
    ((View)inputFields[FIELD_USER].getParent()).setVisibility(View.GONE);
  }
  typeCell[0].setTypeChecked(currentType == 0);
  typeCell[1].setTypeChecked(currentType == 1);
}

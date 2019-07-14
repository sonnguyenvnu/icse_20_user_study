@Override public void onDialogButtonClick(int requestCode,boolean isPositive){
  if (isPositive == false) {
    if (requestCode == DIALOG_PUT_USER) {
      isDataChanged=false;
      finish();
    }
    return;
  }
switch (requestCode) {
case DIALOG_PUT_USER:
    putUser();
  break;
default :
break;
}
}

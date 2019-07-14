public void requestSdCardPermissions(){
  AlertDialog textDialog=AlertDialogsHelper.getTextDialog(this,R.string.sd_card_write_permission_title,R.string.sd_card_permissions_message);
  textDialog.setButton(DialogInterface.BUTTON_POSITIVE,getString(R.string.ok_action).toUpperCase(),new DialogInterface.OnClickListener(){
    @Override public void onClick(    DialogInterface dialogInterface,    int i){
      if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)       startActivityForResult(new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE),REQUEST_CODE_SD_CARD_PERMISSIONS);
    }
  }
);
  textDialog.show();
}

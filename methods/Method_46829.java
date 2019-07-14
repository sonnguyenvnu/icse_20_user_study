public void requestStoragePermission(@NonNull final OnPermissionGranted onPermissionGranted){
  final MaterialDialog materialDialog=GeneralDialogCreation.showBasicDialog(this,R.string.grant_storage_permission,R.string.grantper,R.string.grant,R.string.cancel);
  materialDialog.getActionButton(DialogAction.NEGATIVE).setOnClickListener(v -> finish());
  materialDialog.setCancelable(false);
  requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,STORAGE_PERMISSION,materialDialog,onPermissionGranted);
}

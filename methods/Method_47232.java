@Override public boolean onNavigationItemSelected(@NonNull MenuItem item){
  actionViewStateManager.deselectCurrentActionView();
  actionViewStateManager.selectActionView(item);
  isSomethingSelected=true;
  String title=item.getTitle().toString();
  MenuMetadata meta=dataUtils.getDrawerMetadata(item);
switch (meta.type) {
case MenuMetadata.ITEM_ENTRY:
    if (dataUtils.containsBooks(new String[]{title,meta.path}) != -1) {
      FileUtils.checkForPath(mainActivity,meta.path,mainActivity.isRootExplorer());
    }
  if (dataUtils.getAccounts().size() > 0 && (meta.path.startsWith(CloudHandler.CLOUD_PREFIX_BOX) || meta.path.startsWith(CloudHandler.CLOUD_PREFIX_DROPBOX) || meta.path.startsWith(CloudHandler.CLOUD_PREFIX_ONE_DRIVE) || meta.path.startsWith(CloudHandler.CLOUD_PREFIX_GOOGLE_DRIVE))) {
    CloudUtil.checkToken(meta.path,mainActivity);
  }
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && meta.path.contains(OTGUtil.PREFIX_OTG) && SingletonUsbOtg.getInstance().getUsbOtgRoot() == null) {
  MaterialDialog dialog=GeneralDialogCreation.showOtgSafExplanationDialog(mainActivity);
  dialog.getActionButton(DialogAction.POSITIVE).setOnClickListener((v) -> {
    Intent safIntent=new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
    mainActivity.startActivityForResult(safIntent,MainActivity.REQUEST_CODE_SAF);
    dialog.dismiss();
  }
);
}
 else {
  pendingPath=meta.path;
  closeIfNotLocked();
  if (isLocked()) {
    onDrawerClosed();
  }
}
break;
case MenuMetadata.ITEM_INTENT:
meta.onClickListener.onClick();
break;
}
return true;
}

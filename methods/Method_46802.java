@Override public void onNewIntent(Intent i){
  intent=i;
  path=i.getStringExtra("path");
  if (path != null) {
    if (new File(path).isDirectory()) {
      MainFragment ma=getCurrentMainFragment();
      if (ma != null) {
        ma.loadlist(path,false,OpenMode.FILE);
      }
 else       goToMain(path);
    }
 else     FileUtils.openFile(new File(path),mainActivity,getPrefs());
  }
 else   if (i.getStringArrayListExtra(TAG_INTENT_FILTER_FAILED_OPS) != null) {
    ArrayList<HybridFileParcelable> failedOps=i.getParcelableArrayListExtra(TAG_INTENT_FILTER_FAILED_OPS);
    if (failedOps != null) {
      mainActivityHelper.showFailedOperationDialog(failedOps,this);
    }
  }
 else   if (i.getCategories() != null && i.getCategories().contains(CLOUD_AUTHENTICATOR_GDRIVE)) {
    CloudRail.setAuthenticationResponse(intent);
  }
 else   if ((openProcesses=i.getBooleanExtra(KEY_INTENT_PROCESS_VIEWER,false))) {
    FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.content_frame,new ProcessViewerFragment(),KEY_INTENT_PROCESS_VIEWER);
    drawer.setSomethingSelected(true);
    openProcesses=false;
    transaction.commitAllowingStateLoss();
    supportInvalidateOptionsMenu();
  }
 else   if (intent.getAction() != null) {
    checkForExternalIntent(intent);
    if (SDK_INT >= Build.VERSION_CODES.KITKAT) {
      if (intent.getAction().equals(UsbManager.ACTION_USB_DEVICE_DETACHED)) {
        SingletonUsbOtg.getInstance().resetUsbOtgRoot();
        drawer.refreshDrawer();
      }
    }
  }
}

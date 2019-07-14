private void startActionMode(){
  if (mActionModeCallback == null) {
    mActionModeCallback=new ActionModeCallback(this,new ActionModeCallback.ActionListener(){
      @Override public void onDismissAction(){
        clearSelections();
      }
      @Override public void onDoneAction(){
        if (mSelectedFiles.size() > 0) {
          AddFolderEvent event=new AddFolderEvent(mSelectedFiles);
          RxBus.getInstance().post(event);
          finish();
        }
 else {
          mActionModeCallback.dismiss();
        }
      }
    }
);
  }
  startSupportActionMode(mActionModeCallback);
  mActionModeCallback.setShowing(true);
}

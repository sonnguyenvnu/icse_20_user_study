public void onFinishLogin(){
  handleIntent(passcodeSaveIntent,passcodeSaveIntentIsNew,passcodeSaveIntentIsRestore,true,passcodeSaveIntentAccount,passcodeSaveIntentState);
  actionBarLayout.removeAllFragments();
  if (layersActionBarLayout != null) {
    layersActionBarLayout.removeAllFragments();
  }
  if (backgroundTablet != null) {
    backgroundTablet.setVisibility(View.VISIBLE);
  }
}

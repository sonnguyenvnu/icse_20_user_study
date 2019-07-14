private void updateTabs(){
  if (scrollSlidingTextTabStrip == null) {
    return;
  }
  scrollSlidingTextTabStrip.addTextTab(0,LocaleController.getString("NetworkUsageMobile",R.string.NetworkUsageMobile));
  scrollSlidingTextTabStrip.addTextTab(1,LocaleController.getString("NetworkUsageWiFi",R.string.NetworkUsageWiFi));
  scrollSlidingTextTabStrip.addTextTab(2,LocaleController.getString("NetworkUsageRoaming",R.string.NetworkUsageRoaming));
  scrollSlidingTextTabStrip.setVisibility(View.VISIBLE);
  actionBar.setExtraHeight(AndroidUtilities.dp(44));
  int id=scrollSlidingTextTabStrip.getCurrentTabId();
  if (id >= 0) {
    viewPages[0].selectedType=id;
  }
  scrollSlidingTextTabStrip.finishAddingTabs();
}

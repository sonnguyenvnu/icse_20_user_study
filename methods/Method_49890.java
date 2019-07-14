private boolean ensureMmsConfigLoaded(){
  if (mMmsConfig == null) {
    final MmsConfig config;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
      config=MmsConfigManager.getInstance().getMmsConfigBySubId(mSubId);
    }
 else {
      config=MmsConfigManager.getInstance().getMmsConfig();
    }
    if (config != null) {
      mMmsConfig=new MmsConfig.Overridden(config,mMmsConfigOverrides);
    }
  }
  return mMmsConfig != null;
}

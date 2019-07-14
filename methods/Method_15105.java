@Override public void initData(){
  isToSaveCache=SettingUtil.cache && cacheCallBack != null && cacheCallBack.getCacheClass() != null;
  isToLoadCache=isToSaveCache && StringUtil.isNotEmpty(cacheCallBack.getCacheGroup(),true);
}

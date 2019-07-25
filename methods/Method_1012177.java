public static void init(boolean isShowLog,@Nullable String tag){
  IS_SHOW_LOG=isShowLog;
  mGlobalTag=tag;
  mIsGlobalTagEmpty=TextUtils.isEmpty(mGlobalTag);
}

public void markIntentIfNeeded(Intent intent){
  if (intent.getComponent() == null) {
    return;
  }
  String targetPackageName=intent.getComponent().getPackageName();
  String targetClassName=intent.getComponent().getClassName();
  if (!targetPackageName.equals(mContext.getPackageName()) && mPluginManager.getLoadedPlugin(targetPackageName) != null) {
    intent.putExtra(Constants.KEY_IS_PLUGIN,true);
    intent.putExtra(Constants.KEY_TARGET_PACKAGE,targetPackageName);
    intent.putExtra(Constants.KEY_TARGET_ACTIVITY,targetClassName);
    dispatchStubActivity(intent);
  }
}

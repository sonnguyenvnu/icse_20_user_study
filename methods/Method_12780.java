protected void injectIntent(Intent intent){
  mPluginManager.getComponentsHandler().transformIntentToExplicitAsNeeded(intent);
  if (intent.getComponent() != null) {
    Log.i(TAG,String.format("execStartActivity[%s : %s]",intent.getComponent().getPackageName(),intent.getComponent().getClassName()));
    this.mPluginManager.getComponentsHandler().markIntentIfNeeded(intent);
  }
}

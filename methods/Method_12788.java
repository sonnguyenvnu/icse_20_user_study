protected LoadedPlugin createLoadedPlugin(File apk) throws Exception {
  return new LoadedPlugin(this,this.mContext,apk);
}

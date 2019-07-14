@VisibleForTesting public void setUseDrawee(boolean useDrawee){
  mUseDrawee=useDrawee;
  supportInvalidateOptionsMenu();
  setLoaderAdapter(mCurrentLoaderAdapterIndex);
  setSourceAdapter(mCurrentSourceAdapterIndex);
}

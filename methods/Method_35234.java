@Override public void restoreState(Parcelable state,ClassLoader loader){
  Bundle bundle=(Bundle)state;
  if (state != null) {
    savesState=bundle.getBoolean(KEY_SAVES_STATE,false);
    savedPages=bundle.getSparseParcelableArray(KEY_SAVED_PAGES);
    int[] visiblePageIdsKeys=bundle.getIntArray(KEY_VISIBLE_PAGE_IDS_KEYS);
    String[] visiblePageIdsValues=bundle.getStringArray(KEY_VISIBLE_PAGE_IDS_VALUES);
    visiblePageIds=new SparseArray<>(visiblePageIdsKeys.length);
    for (int i=0; i < visiblePageIdsKeys.length; i++) {
      visiblePageIds.put(visiblePageIdsKeys[i],visiblePageIdsValues[i]);
    }
  }
}

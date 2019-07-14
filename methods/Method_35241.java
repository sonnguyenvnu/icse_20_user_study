@Override public void restoreState(Parcelable state,ClassLoader loader){
  Bundle bundle=(Bundle)state;
  if (state != null) {
    savedPages=bundle.getSparseParcelableArray(KEY_SAVED_PAGES);
    maxPagesToStateSave=bundle.getInt(KEY_MAX_PAGES_TO_STATE_SAVE);
    savedPageHistory=bundle.getIntegerArrayList(KEY_SAVE_PAGE_HISTORY);
  }
}

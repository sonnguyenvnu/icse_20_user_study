/** 
 * This loads a path into the MainFragment.
 * @param path the path to be loaded
 * @param back if we're coming back from any directory and want the scroll to be restored
 * @param openMode the mode in which the directory should be opened
 */
public void loadlist(final String path,final boolean back,final OpenMode openMode){
  if (mActionMode != null)   mActionMode.finish();
  mSwipeRefreshLayout.setRefreshing(true);
  if (loadFilesListTask != null && loadFilesListTask.getStatus() == AsyncTask.Status.RUNNING) {
    loadFilesListTask.cancel(true);
  }
  loadFilesListTask=new LoadFilesListTask(ma.getActivity(),path,ma,openMode,getBoolean(PREFERENCE_SHOW_THUMB),getBoolean(PREFERENCE_SHOW_HIDDENFILES),(data) -> {
    if (data != null && data.second != null) {
      boolean isPathLayoutGrid=dataUtils.getListOrGridForPath(path,DataUtils.LIST) == DataUtils.GRID;
      setListElements(data.second,back,path,data.first,false,isPathLayoutGrid);
      mSwipeRefreshLayout.setRefreshing(false);
    }
  }
);
  loadFilesListTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
}

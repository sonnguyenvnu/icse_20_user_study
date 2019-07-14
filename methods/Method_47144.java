public void onSearchCompleted(final String query){
  if (!results) {
    LIST_ELEMENTS.clear();
  }
  new AsyncTask<Void,Void,Void>(){
    @Override protected Void doInBackground(    Void... params){
      Collections.sort(LIST_ELEMENTS,new FileListSorter(dsort,sortby,asc));
      return null;
    }
    @Override public void onPostExecute(    Void c){
      reloadListElements(true,true,!IS_LIST);
      getMainActivity().getAppbar().getBottomBar().setPathText("");
      getMainActivity().getAppbar().getBottomBar().setFullPathText(getString(R.string.searchresults,query));
    }
  }
.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
}

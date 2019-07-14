/** 
 * Creates a fragment which will handle the search AsyncTask  {@link SearchWorkerFragment}
 * @param query the text query entered the by user
 */
public void search(SharedPreferences sharedPrefs,String query){
  TabFragment tabFragment=mainActivity.getTabFragment();
  if (tabFragment == null)   return;
  final MainFragment ma=(MainFragment)tabFragment.getCurrentTabFragment();
  final String fpath=ma.getCurrentPath();
  SEARCH_TEXT=query;
  mainActivity.mainFragment=(MainFragment)mainActivity.getTabFragment().getCurrentTabFragment();
  FragmentManager fm=mainActivity.getSupportFragmentManager();
  SearchWorkerFragment fragment=(SearchWorkerFragment)fm.findFragmentByTag(MainActivity.TAG_ASYNC_HELPER);
  if (fragment != null) {
    if (fragment.mSearchAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
      fragment.mSearchAsyncTask.cancel(true);
    }
    fm.beginTransaction().remove(fragment).commit();
  }
  addSearchFragment(fm,new SearchWorkerFragment(),fpath,query,ma.openMode,mainActivity.isRootExplorer(),sharedPrefs.getBoolean(SearchWorkerFragment.KEY_REGEX,false),sharedPrefs.getBoolean(SearchWorkerFragment.KEY_REGEX_MATCHES,false));
}

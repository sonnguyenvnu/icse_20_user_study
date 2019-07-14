void switchToList(){
  IS_LIST=true;
  if (utilsProvider.getAppTheme().equals(AppTheme.LIGHT)) {
    listView.setBackgroundDrawable(null);
  }
  if (mLayoutManager == null)   mLayoutManager=new LinearLayoutManager(getActivity());
  listView.setLayoutManager(mLayoutManager);
  listView.clearOnScrollListeners();
  adapter=null;
}

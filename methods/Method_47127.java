void onSavedInstanceState(final Bundle savedInstanceState){
  Bundle b=new Bundle();
  String cur=savedInstanceState.getString("CURRENT_PATH");
  if (cur != null) {
    b.putInt("index",savedInstanceState.getInt("index"));
    b.putInt("top",savedInstanceState.getInt("top"));
    scrolls.put(cur,b);
    openMode=OpenMode.getOpenMode(savedInstanceState.getInt("openMode",0));
    if (openMode == OpenMode.SMB)     smbPath=savedInstanceState.getString("SmbPath");
    LIST_ELEMENTS=savedInstanceState.getParcelableArrayList("list");
    CURRENT_PATH=cur;
    folder_count=savedInstanceState.getInt("folder_count",0);
    file_count=savedInstanceState.getInt("file_count",0);
    results=savedInstanceState.getBoolean("results");
    getMainActivity().getAppbar().getBottomBar().updatePath(CURRENT_PATH,results,MainActivityHelper.SEARCH_TEXT,openMode,folder_count,file_count,this);
    reloadListElements(true,results,!IS_LIST);
    if (savedInstanceState.getBoolean("selection")) {
      for (      Integer index : savedInstanceState.getIntegerArrayList("position")) {
        adapter.toggleChecked(index,null);
      }
    }
  }
}

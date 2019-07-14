@Override public void onActivityCreated(Bundle savedInstanceState){
  super.onActivityCreated(savedInstanceState);
  setRetainInstance(true);
  MainActivity mainActivity=(MainActivity)getActivity();
  mainActivity.getAppbar().setTitle(R.string.apps);
  mainActivity.floatingActionButton.getMenuButton().hide();
  mainActivity.getAppbar().getBottomBar().setVisibility(View.GONE);
  mainActivity.supportInvalidateOptionsMenu();
  vl=getListView();
  Sp=PreferenceManager.getDefaultSharedPreferences(getActivity());
  getSortModes();
  ListView vl=getListView();
  vl.setDivider(null);
  if (utilsProvider.getAppTheme().equals(AppTheme.DARK))   getActivity().getWindow().getDecorView().setBackgroundColor(Utils.getColor(getContext(),R.color.holo_dark_background));
 else   if (utilsProvider.getAppTheme().equals(AppTheme.BLACK))   getActivity().getWindow().getDecorView().setBackgroundColor(Utils.getColor(getContext(),android.R.color.black));
  modelProvider=new AppsAdapterPreloadModel(app);
  ViewPreloadSizeProvider<String> sizeProvider=new ViewPreloadSizeProvider<>();
  ListPreloader<String> preloader=new ListPreloader<>(GlideApp.with(app),modelProvider,sizeProvider,GlideConstants.MAX_PRELOAD_APPSADAPTER);
  adapter=new AppsAdapter(getContext(),(ThemedActivity)getActivity(),utilsProvider,modelProvider,sizeProvider,R.layout.rowlayout,app,Sp);
  getListView().setOnScrollListener(preloader);
  setListAdapter(adapter);
  setListShown(false);
  setEmptyText(getString(R.string.no_applications));
  getLoaderManager().initLoader(ID_LOADER_APP_LIST,null,this);
  if (savedInstanceState != null) {
    index=savedInstanceState.getInt(KEY_INDEX);
    top=savedInstanceState.getInt(KEY_TOP);
  }
}

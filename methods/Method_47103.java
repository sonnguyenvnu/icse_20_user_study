@Override public void onActivityCreated(Bundle savedInstanceState){
  super.onActivityCreated(savedInstanceState);
  SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(getActivity());
  compressedFile=new File(Uri.parse(getArguments().getString(KEY_PATH)).getPath());
  mToolbarContainer=mainActivity.getAppbar().getAppbarLayout();
  mToolbarContainer.setOnTouchListener((view,motionEvent) -> {
    if (stopAnims) {
      if ((!compressedExplorerAdapter.stoppedAnimation)) {
        stopAnim();
      }
      compressedExplorerAdapter.stoppedAnimation=true;
    }
    stopAnims=false;
    return false;
  }
);
  listView.setVisibility(View.VISIBLE);
  mLayoutManager=new LinearLayoutManager(getActivity());
  listView.setLayoutManager(mLayoutManager);
  if (utilsProvider.getAppTheme().equals(AppTheme.DARK)) {
    rootView.setBackgroundColor(Utils.getColor(getContext(),R.color.holo_dark_background));
  }
 else   if (utilsProvider.getAppTheme().equals(AppTheme.BLACK)) {
    listView.setBackgroundColor(Utils.getColor(getContext(),android.R.color.black));
  }
 else {
    listView.setBackgroundColor(Utils.getColor(getContext(),android.R.color.background_light));
  }
  gobackitem=sp.getBoolean(PreferencesConstants.PREFERENCE_SHOW_GOBACK_BUTTON,false);
  coloriseIcons=sp.getBoolean(PreferencesConstants.PREFERENCE_COLORIZE_ICONS,true);
  showSize=sp.getBoolean(PreferencesConstants.PREFERENCE_SHOW_FILE_SIZE,false);
  showLastModified=sp.getBoolean(PreferencesConstants.PREFERENCE_SHOW_LAST_MODIFIED,true);
  showDividers=sp.getBoolean(PreferencesConstants.PREFERENCE_SHOW_DIVIDERS,true);
  year=("" + Calendar.getInstance().get(Calendar.YEAR)).substring(2,4);
  accentColor=mainActivity.getAccent();
  iconskin=mainActivity.getCurrentColorPreference().iconSkin;
  if (savedInstanceState == null && compressedFile != null) {
    files=new ArrayList<>();
    String fileName=compressedFile.getName().substring(0,compressedFile.getName().lastIndexOf("."));
    String path=getActivity().getExternalCacheDir().getPath() + SEPARATOR + fileName;
    files.add(new HybridFileParcelable(path));
    decompressor=CompressedHelper.getCompressorInstance(getContext(),compressedFile);
    changePath("");
  }
 else {
    onRestoreInstanceState(savedInstanceState);
  }
  mainActivity.supportInvalidateOptionsMenu();
}

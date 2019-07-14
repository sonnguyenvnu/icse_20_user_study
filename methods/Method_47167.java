@Override public void setupDialog(Dialog dialog,int style){
  super.setupDialog(dialog,style);
  rootView=View.inflate(getContext(),R.layout.fragment_sheet_properties,null);
  dialog.setContentView(rootView);
  mBundle=getArguments();
  mFile=mBundle.getParcelable(KEY_FILE);
  mPermission=mBundle.getString(KEY_PERMISSION);
  mIsRoot=mBundle.getBoolean(KEY_ROOT);
  mToolbar=rootView.findViewById(R.id.collapsing_toolbar_layout);
  mAppBarLayout=rootView.findViewById(R.id.appBarLayout);
  mToolbar.setTitle(getString(R.string.properties));
  mToolbar.setCollapsedTitleTextAppearance(R.style.collapsed_appbar);
  mToolbar.setExpandedTitleTextAppearance(R.style.expanded_appbar);
  mFileNameTextView=rootView.findViewById(R.id.text_view_file_name);
  mFileNameTextView.setText(mFile.getName());
  mFileTypeTextView=rootView.findViewById(R.id.text_view_file_type);
  mFileTypeTextView.setText(mFile.isDirectory() ? getString(R.string.folder) : mFile.getName().substring(mFile.getName().lastIndexOf(".")));
  mFileSizeTextView=rootView.findViewById(R.id.text_view_file_size);
  mFileSizeTextView.setText(Formatter.formatFileSize(dialog.getContext(),mFile.isDirectory() ? FileUtils.folderSize(new File(mFile.getPath()),null) : mFile.getSize()));
  mFileLocationTextView=rootView.findViewById(R.id.text_view_file_location);
  mFileLocationTextView.setText(mFile.getPath());
  mFileAccessedTextView=rootView.findViewById(R.id.text_view_file_accessed);
  mFileAccessedTextView.setText(Utils.getDate(mFile.getDate()));
  mFileModifiedTextView=rootView.findViewById(R.id.text_view_file_modified);
  mFileModifiedTextView.setText(Utils.getDate(mFile.getDate()));
  CoordinatorLayout.LayoutParams layoutParams=(CoordinatorLayout.LayoutParams)((View)rootView.getParent()).getLayoutParams();
  mBottomSheetBehavior=(BottomSheetBehavior)layoutParams.getBehavior();
  mBottomSheetBehavior.setBottomSheetCallback(mCallback);
  mNestedScrollView=rootView.findViewById(R.id.nested_view);
  mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener(){
    @Override public void onScrollChange(    NestedScrollView v,    int scrollX,    int scrollY,    int oldScrollX,    int oldScrollY){
      Log.d(getClass().getSimpleName(),"ScrollY: " + scrollY + " oldScrollY: " + oldScrollY);
      if (scrollY == 0) {
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
      }
    }
  }
);
}

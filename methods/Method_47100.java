@Override public void setupDialog(Dialog dialog,int style){
  super.setupDialog(dialog,style);
  rootView=getActivity().getLayoutInflater().inflate(R.layout.fragment_sheet_cloud,null);
  if (((MainActivity)getActivity()).getAppTheme().equals(AppTheme.DARK)) {
    rootView.setBackgroundColor(Utils.getColor(getContext(),R.color.holo_dark_background));
  }
 else   if (((MainActivity)getActivity()).getAppTheme().equals(AppTheme.BLACK)) {
    rootView.setBackgroundColor(Utils.getColor(getContext(),android.R.color.black));
  }
 else {
    rootView.setBackgroundColor(Utils.getColor(getContext(),android.R.color.white));
  }
  mSmbLayout=rootView.findViewById(R.id.linear_layout_smb);
  mScpLayout=rootView.findViewById(R.id.linear_layout_scp);
  mBoxLayout=rootView.findViewById(R.id.linear_layout_box);
  mDropboxLayout=rootView.findViewById(R.id.linear_layout_dropbox);
  mGoogleDriveLayout=rootView.findViewById(R.id.linear_layout_google_drive);
  mOnedriveLayout=rootView.findViewById(R.id.linear_layout_onedrive);
  mGetCloudLayout=rootView.findViewById(R.id.linear_layout_get_cloud);
  if (isCloudProviderAvailable(getContext())) {
    mBoxLayout.setVisibility(View.VISIBLE);
    mDropboxLayout.setVisibility(View.VISIBLE);
    mGoogleDriveLayout.setVisibility(View.VISIBLE);
    mOnedriveLayout.setVisibility(View.VISIBLE);
    mGetCloudLayout.setVisibility(View.GONE);
  }
  mSmbLayout.setOnClickListener(this);
  mScpLayout.setOnClickListener(this);
  mBoxLayout.setOnClickListener(this);
  mDropboxLayout.setOnClickListener(this);
  mGoogleDriveLayout.setOnClickListener(this);
  mOnedriveLayout.setOnClickListener(this);
  mGetCloudLayout.setOnClickListener(this);
  dialog.setContentView(rootView);
}

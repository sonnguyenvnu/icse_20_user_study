private void export(View v){
  final List<String> backups=asList(StorageHelper.getExternalStoragePublicDir().list());
  SimpleDateFormat sdf=new SimpleDateFormat(Constants.DATE_FORMAT_EXPORT);
  String fileName=sdf.format(Calendar.getInstance().getTime());
  final EditText fileNameEditText=v.findViewById(R.id.export_file_name);
  final TextView backupExistingTextView=v.findViewById(R.id.backup_existing);
  fileNameEditText.setHint(fileName);
  fileNameEditText.addTextChangedListener(new TextWatcher(){
    @Override public void onTextChanged(    CharSequence arg0,    int arg1,    int arg2,    int arg3){
    }
    @Override public void beforeTextChanged(    CharSequence arg0,    int arg1,    int arg2,    int arg3){
    }
    @Override public void afterTextChanged(    Editable arg0){
      if (backups.contains(arg0.toString())) {
        backupExistingTextView.setText(R.string.backup_existing);
      }
 else {
        backupExistingTextView.setText("");
      }
    }
  }
);
  new MaterialDialog.Builder(getActivity()).title(R.string.data_export_message).customView(v,false).positiveText(R.string.confirm).onPositive((dialog,which) -> {
    ((OmniNotes)getActivity().getApplication()).getAnalyticsHelper().trackEvent(AnalyticsHelper.CATEGORIES.SETTING,"settings_export_data");
    String backupName=StringUtils.isEmpty(fileNameEditText.getText().toString()) ? fileNameEditText.getHint().toString() : fileNameEditText.getText().toString();
    BackupHelper.startBackupService(backupName);
  }
).build().show();
}

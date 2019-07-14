private void loadEditDialog(final PathSwitchPreference p){
  int fab_skin=activity.getAccent();
  LayoutInflater li=LayoutInflater.from(activity);
  final View v=li.inflate(R.layout.dialog_twoedittexts,null);
  ((TextInputLayout)v.findViewById(R.id.text_input1)).setHint(getString(R.string.name));
  ((TextInputLayout)v.findViewById(R.id.text_input2)).setHint(getString(R.string.directory));
  final EditText editText1=v.findViewById(R.id.text1), editText2=v.findViewById(R.id.text2);
  editText1.setText(p.getTitle());
  editText2.setText(p.getSummary());
  final MaterialDialog dialog=new MaterialDialog.Builder(getActivity()).title(R.string.edit_shortcut).theme(activity.getAppTheme().getMaterialDialogTheme()).positiveColor(fab_skin).positiveText(getString(R.string.edit).toUpperCase()).negativeColor(fab_skin).negativeText(android.R.string.cancel).customView(v,false).build();
  dialog.getActionButton(DialogAction.POSITIVE).setEnabled(FileUtils.isPathAccessible(editText2.getText().toString(),sharedPrefs));
  disableButtonIfTitleEmpty(editText1,dialog);
  disableButtonIfNotPath(editText2,dialog);
  dialog.getActionButton(DialogAction.POSITIVE).setOnClickListener(view -> {
    final String oldName=p.getTitle().toString();
    final String oldPath=p.getSummary().toString();
    dataUtils.removeBook(position.get(p));
    position.remove(p);
    getPreferenceScreen().removePreference(p);
    p.setTitle(editText1.getText());
    p.setSummary(editText2.getText());
    position.put(p,position.size());
    getPreferenceScreen().addPreference(p);
    String[] values=new String[]{editText1.getText().toString(),editText2.getText().toString()};
    dataUtils.addBook(values);
    AppConfig.runInBackground(() -> utilsHandler.renameBookmark(oldName,oldPath,editText1.getText().toString(),editText2.getText().toString()));
    dialog.dismiss();
  }
);
  dialog.show();
}

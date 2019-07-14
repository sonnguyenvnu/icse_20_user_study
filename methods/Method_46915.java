private void showDialog(final String path,final ArrayList<HybridFileParcelable> filesToCopy,final ArrayList<HybridFileParcelable> conflictingFiles){
  int accentColor=mainActivity.getAccent();
  final MaterialDialog.Builder dialogBuilder=new MaterialDialog.Builder(context);
  LayoutInflater layoutInflater=(LayoutInflater)mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  View view=layoutInflater.inflate(R.layout.copy_dialog,null);
  dialogBuilder.customView(view,true);
  TextView textView=view.findViewById(R.id.fileNameText);
  textView.setText(conflictingFiles.get(counter).getName());
  final CheckBox checkBox=view.findViewById(R.id.checkBox);
  Utils.setTint(context,checkBox,accentColor);
  dialogBuilder.theme(mainActivity.getAppTheme().getMaterialDialogTheme());
  dialogBuilder.title(context.getResources().getString(R.string.paste));
  dialogBuilder.positiveText(R.string.skip);
  dialogBuilder.negativeText(R.string.overwrite);
  dialogBuilder.neutralText(R.string.cancel);
  dialogBuilder.positiveColor(accentColor);
  dialogBuilder.negativeColor(accentColor);
  dialogBuilder.neutralColor(accentColor);
  dialogBuilder.onPositive((dialog,which) -> {
    if (checkBox.isChecked())     dialogState=DO_FOR_ALL_ELEMENTS.DO_NOT_REPLACE;
    doNotReplaceFiles(path,filesToCopy,conflictingFiles);
  }
);
  dialogBuilder.onNegative((dialog,which) -> {
    if (checkBox.isChecked())     dialogState=DO_FOR_ALL_ELEMENTS.REPLACE;
    replaceFiles(path,filesToCopy,conflictingFiles);
  }
);
  final MaterialDialog dialog=dialogBuilder.build();
  dialog.show();
  if (filesToCopy.get(0).getParent().equals(path)) {
    View negative=dialog.getActionButton(DialogAction.NEGATIVE);
    negative.setEnabled(false);
  }
}

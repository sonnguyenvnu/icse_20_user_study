public void showFailedOperationDialog(ArrayList<HybridFileParcelable> failedOps,Context contextc){
  MaterialDialog.Builder mat=new MaterialDialog.Builder(contextc);
  mat.title(contextc.getString(R.string.operationunsuccesful));
  mat.theme(mainActivity.getAppTheme().getMaterialDialogTheme());
  mat.positiveColor(accentColor);
  mat.positiveText(R.string.cancel);
  String content=contextc.getString(R.string.operation_fail_following);
  int k=1;
  for (  HybridFileParcelable s : failedOps) {
    content=content + "\n" + (k) + ". " + s.getName();
    k++;
  }
  mat.content(content);
  mat.build().show();
}

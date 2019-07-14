public static void showArchiveDialog(final File f,final MainActivity m){
  int accentColor=m.getAccent();
  MaterialDialog.Builder mat=new MaterialDialog.Builder(m);
  mat.title(R.string.archive).content(R.string.archtext).positiveText(R.string.extract).negativeText(R.string.view).neutralText(R.string.cancel).positiveColor(accentColor).negativeColor(accentColor).neutralColor(accentColor).onPositive((dialog,which) -> m.mainActivityHelper.extractFile(f)).onNegative((dialog,which) -> m.openCompressed(Uri.fromFile(f).toString()));
  if (m.getAppTheme().equals(AppTheme.DARK) || m.getAppTheme().equals(AppTheme.BLACK))   mat.theme(Theme.DARK);
  MaterialDialog b=mat.build();
  if (!CompressedHelper.isFileExtractable(f.getPath())) {
    b.getActionButton(DialogAction.NEGATIVE).setEnabled(false);
  }
  b.show();
}

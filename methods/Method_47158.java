private void loadDeleteDialog(final PathSwitchPreference p){
  int fab_skin=activity.getAccent();
  final MaterialDialog dialog=new MaterialDialog.Builder(getActivity()).title(R.string.questiondelete_shortcut).theme(activity.getAppTheme().getMaterialDialogTheme()).positiveColor(fab_skin).positiveText(getString(R.string.delete).toUpperCase()).negativeColor(fab_skin).negativeText(android.R.string.cancel).build();
  dialog.getActionButton(DialogAction.POSITIVE).setOnClickListener(view -> {
    dataUtils.removeBook(position.get(p));
    utilsHandler.removeFromDatabase(new OperationData(UtilsHandler.Operation.BOOKMARKS,p.getTitle().toString(),p.getSummary().toString()));
    getPreferenceScreen().removePreference(p);
    position.remove(p);
    dialog.dismiss();
  }
);
  dialog.show();
}

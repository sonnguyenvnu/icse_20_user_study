public static void showSortDialog(final AppsListFragment m,AppTheme appTheme){
  int accentColor=((ThemedActivity)m.getActivity()).getAccent();
  String[] sort=m.getResources().getStringArray(R.array.sortbyApps);
  int current=Integer.parseInt(m.Sp.getString("sortbyApps","0"));
  MaterialDialog.Builder a=new MaterialDialog.Builder(m.getActivity());
  a.theme(appTheme.getMaterialDialogTheme());
  a.items(sort).itemsCallbackSingleChoice(current > 2 ? current - 3 : current,(dialog,view,which,text) -> true);
  a.negativeText(R.string.ascending).positiveColor(accentColor);
  a.positiveText(R.string.descending).negativeColor(accentColor);
  a.onNegative((dialog,which) -> {
    m.Sp.edit().putString("sortbyApps","" + dialog.getSelectedIndex()).commit();
    m.getSortModes();
    m.getLoaderManager().restartLoader(AppsListFragment.ID_LOADER_APP_LIST,null,m);
    dialog.dismiss();
  }
);
  a.onPositive((dialog,which) -> {
    m.Sp.edit().putString("sortbyApps","" + (dialog.getSelectedIndex() + 3)).commit();
    m.getSortModes();
    m.getLoaderManager().restartLoader(AppsListFragment.ID_LOADER_APP_LIST,null,m);
    dialog.dismiss();
  }
);
  a.title(R.string.sortby);
  a.build().show();
}

private static void onSortTypeSelected(MainFragment m,SharedPreferences sharedPref,Set<String> onlyThisFloders,MaterialDialog dialog,boolean desc){
  final int sortType=desc ? dialog.getSelectedIndex() + 4 : dialog.getSelectedIndex();
  SortHandler sortHandler=new SortHandler(m.getContext());
  if (onlyThisFloders.contains(m.getCurrentPath())) {
    Sort oldSort=sortHandler.findEntry(m.getCurrentPath());
    Sort newSort=new Sort(m.getCurrentPath(),sortType);
    if (oldSort == null) {
      sortHandler.addEntry(newSort);
    }
 else {
      sortHandler.updateEntry(oldSort,newSort);
    }
  }
 else {
    sortHandler.clear(m.getCurrentPath());
    sharedPref.edit().putString("sortby",String.valueOf(sortType)).apply();
  }
  sharedPref.edit().putStringSet(PREFERENCE_SORTBY_ONLY_THIS,onlyThisFloders).apply();
  m.updateList();
  dialog.dismiss();
}

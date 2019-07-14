private void updateGridRecyclerLayoutSummary(){
  final ListPreference listPreference=(ListPreference)findPreference(Const.RECYCLER_LAYOUT_KEY);
  final ListPreference gridPreference=(ListPreference)findPreference(Const.GRID_SPAN_COUNT_KEY);
  final String value=listPreference.getValue();
  final boolean gridGroupVisible=Const.GRID_RECYCLER_VIEW_LAYOUT_VALUE.equals(value);
  if (gridGroupVisible) {
    final String spanCountValue=gridPreference.getValue();
    gridPreference.setSummary(getString(R.string.label_grid_recycler_span_count_summary,spanCountValue));
  }
  gridPreference.setVisible(gridGroupVisible);
}

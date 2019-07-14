private void updateInfiniteDataSourceSummary(final Preference preference){
  final boolean currentState=updateCheckBoxPreference(getResources(),(CheckBoxPreference)preference,R.string.checked_infinite_data_source_summary,R.string.unchecked_infinite_data_source_summary);
  findPreference(Const.DISTINCT_DATA_SOURCE_KEY).setEnabled(currentState);
}

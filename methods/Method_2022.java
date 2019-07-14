private void updateNumberOfDecodingThreadSummary(final Preference preference){
  final ListPreference listPreference=(ListPreference)preference;
  final int valueIndex=listPreference.findIndexOfValue(listPreference.getValue());
  String summary=getResources().getStringArray(R.array.decoding_thread_summaries)[valueIndex];
  if (valueIndex == 0) {
    summary+=Const.NUMBER_OF_PROCESSORS;
  }
  preference.setSummary(summary);
}

private static void updateListPreference(Resources resources,ListPreference preference,int arrayValuesId){
  final int valueIndex=preference.findIndexOfValue(preference.getValue());
  final String summary=resources.getStringArray(arrayValuesId)[valueIndex];
  preference.setSummary(summary);
}

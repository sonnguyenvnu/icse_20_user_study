private static boolean updateCheckBoxPreference(Resources resources,CheckBoxPreference preference,int checkedSummaryRes,int uncheckedSummaryRes){
  final boolean checkboxState=preference.isChecked();
  if (checkboxState) {
    preference.setSummary(resources.getString(checkedSummaryRes));
  }
 else {
    preference.setSummary(resources.getString(uncheckedSummaryRes));
  }
  return checkboxState;
}

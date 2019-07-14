private void refreshAnimation(){
  final Entry spinnerEntry=mSpinnerEntries[mSpinner.getSelectedItemPosition()];
  setAnimationUri(spinnerEntry.uri);
}

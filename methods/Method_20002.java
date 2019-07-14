@Nullable private String getSelectedCity(){
  String selected=(String)mCitySpinner.getSelectedItem();
  if (getString(R.string.value_any_city).equals(selected)) {
    return null;
  }
 else {
    return selected;
  }
}

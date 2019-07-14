@Override public void onDescriptionError(boolean isEmptyDesc){
  description.setError(isEmptyDesc ? getString(R.string.required_field) : null);
}

@Override public void onShowTitleError(boolean isError){
  title.setError(isError ? getString(R.string.required_field) : null);
}

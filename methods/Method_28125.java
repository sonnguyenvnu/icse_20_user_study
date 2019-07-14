@Override public void onTitleError(boolean isEmptyTitle){
  title.setError(isEmptyTitle ? getString(R.string.required_field) : null);
}

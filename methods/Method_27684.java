@Override public void onEmptyUserName(boolean isEmpty){
  username.setError(isEmpty ? getString(R.string.required_field) : null);
}

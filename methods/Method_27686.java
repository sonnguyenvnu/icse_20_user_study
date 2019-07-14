@Override public void onEmptyPassword(boolean isEmpty){
  password.setError(isEmpty ? getString(R.string.required_field) : null);
}

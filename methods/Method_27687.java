@Override public void onEmptyEndpoint(boolean isEmpty){
  endpoint.setError(isEmpty ? getString(R.string.required_field) : null);
}

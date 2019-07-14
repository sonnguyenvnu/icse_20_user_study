private boolean validateForm(){
  boolean result=true;
  if (TextUtils.isEmpty(mEmailField.getText().toString())) {
    mEmailField.setError("Required");
    result=false;
  }
 else {
    mEmailField.setError(null);
  }
  if (TextUtils.isEmpty(mPasswordField.getText().toString())) {
    mPasswordField.setError("Required");
    result=false;
  }
 else {
    mPasswordField.setError(null);
  }
  return result;
}

private boolean validateForm(){
  boolean valid=true;
  String email=mEmailField.getText().toString();
  if (TextUtils.isEmpty(email)) {
    mEmailField.setError("Required.");
    valid=false;
  }
 else {
    mEmailField.setError(null);
  }
  String password=mPasswordField.getText().toString();
  if (TextUtils.isEmpty(password)) {
    mPasswordField.setError("Required.");
    valid=false;
  }
 else {
    mPasswordField.setError(null);
  }
  return valid;
}

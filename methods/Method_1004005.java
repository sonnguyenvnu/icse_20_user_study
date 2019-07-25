@Override public void login(){
  String email=mActivityLoginBinding.etEmail.getText().toString();
  String password=mActivityLoginBinding.etPassword.getText().toString();
  if (mLoginViewModel.isEmailAndPasswordValid(email,password)) {
    hideKeyboard();
    mLoginViewModel.login(email,password);
  }
 else {
    Toast.makeText(this,getString(R.string.invalid_email_password),Toast.LENGTH_SHORT).show();
  }
}

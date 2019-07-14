@OnClick(R.id.ll_security) public void onSecurityClicked(View view){
  if (Security.isPasswordSet()) {
    askPassword();
  }
 else   startActivity(new Intent(getApplicationContext(),SecurityActivity.class));
}

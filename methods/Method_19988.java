private void onAuthSuccess(FirebaseUser user){
  String username=usernameFromEmail(user.getEmail());
  writeNewUser(user.getUid(),username,user.getEmail());
  startActivity(new Intent(SignInActivity.this,MainActivity.class));
  finish();
}

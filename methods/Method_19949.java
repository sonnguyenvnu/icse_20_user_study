@Override public void onClick(View v){
switch (v.getId()) {
case R.id.genericSignInButton:
    signIn();
  break;
case R.id.signOutButton:
mAuth.signOut();
updateUI(null);
break;
}
}
